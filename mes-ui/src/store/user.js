import { defineStore } from 'pinia'
import request from '@/utils/request'
import { getToken, setToken, removeToken } from '@/utils/auth'

const useUserStore = defineStore('user', {
  state: () => ({
    token: getToken(),
    name: '',
    nickName: '',
    avatar: '',
    roles: [],
    permissions: []
  }),
  actions: {
    // 登录
    login(userInfo) {
      const { username, password, code, uuid } = userInfo
      return request({
        url: '/login',
        method: 'post',
        data: { username: username.trim(), password: password, code, uuid }
      }).then((res) => {
        setToken(res.token)
        this.token = res.token
        return res
      })
    },
    // 获取用户信息
    getInfo() {
      return request({ url: '/getInfo', method: 'get' }).then((res) => {
        const user = res.user || {}
        this.name = user.userName || ''
        this.nickName = user.nickName || this.name
        this.avatar = user.avatar || ''
        this.roles = res.roles && res.roles.length > 0 ? res.roles : ['ROLE_DEFAULT']
        this.permissions = res.permissions || []
        return res
      })
    },
    // 退出登录
    logout() {
      return request({ url: '/logout', method: 'post' })
        .catch(() => {})
        .finally(() => {
          this.token = ''
          this.name = ''
          this.nickName = ''
          this.avatar = ''
          this.roles = []
          this.permissions = []
          removeToken()
        })
    },
    // 权限判断
    hasPermi(permission) {
      if (!permission) {
        return true
      }
      if (this.permissions.length === 0) {
        // 权限未加载时不隐藏按钮，交由后端最终校验
        return true
      }
      return (
        this.permissions.includes('*:*:*') ||
        this.permissions.includes(permission)
      )
    }
  }
})

export default useUserStore
