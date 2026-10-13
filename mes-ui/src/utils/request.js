import axios from 'axios'
import { ElMessage } from 'element-plus'
import { getToken, removeToken } from '@/utils/auth'

// 是否已经弹出过期提示，避免重复弹窗
let isRelogining = false

const service = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API,
  timeout: 30000
})

// 请求拦截器：携带 token
service.interceptors.request.use(
  (config) => {
    const token = getToken()
    if (token) {
      config.headers['Authorization'] = 'Bearer ' + token
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// 响应拦截器：按若依统一响应体处理
service.interceptors.response.use(
  (response) => {
    // blob / arraybuffer 直接返回二进制数据（用于导出下载）
    const responseType = response.config && response.config.responseType
    if (responseType === 'blob' || responseType === 'arraybuffer') {
      return response.data
    }

    const res = response.data
    const code = res.code === undefined ? 200 : res.code

    if (code === 200) {
      return res
    }

    if (code === 401) {
      if (!isRelogining) {
        isRelogining = true
        ElMessage.warning('登录状态已过期，请重新登录')
        removeToken()
        // 动态导入避免与 store/router 形成循环依赖
        import('@/router')
          .then((m) => m.default.push('/login'))
          .finally(() => {
            setTimeout(() => {
              isRelogining = false
            }, 1000)
          })
      }
      return Promise.reject(new Error('登录状态已过期，请重新登录'))
    }

    if (!(response.config && response.config.silent)) {
      ElMessage.error(res.msg || '操作失败')
    }
    return Promise.reject(new Error(res.msg || '操作失败'))
  },
  (error) => {
    const silent = error.config && error.config.silent
    let message = error.message || '请求失败'
    if (message.includes('timeout')) {
      message = '接口请求超时，请稍后重试'
    } else if (message.includes('Network Error')) {
      message = '后端接口连接异常'
    } else if (message.includes('Request failed with status code')) {
      message = '接口请求异常：' + message
    }
    if (!silent) {
      ElMessage.error(message)
    }
    return Promise.reject(error)
  }
)

export default service
