<template>
  <div class="login-container">
    <el-card class="login-card">
      <div class="login-header">
        <h2>MES 制造执行系统</h2>
        <p>Manufacturing Execution System</p>
      </div>
      <el-form ref="loginFormRef" :model="loginForm" :rules="rules" @keyup.enter="handleLogin">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" placeholder="请输入账号" size="large" clearable>
            <template #prefix><el-icon><User /></el-icon></template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            size="large"
            show-password
          >
            <template #prefix><el-icon><Lock /></el-icon></template>
          </el-input>
        </el-form-item>
        <el-form-item v-if="captchaEnabled" prop="code">
          <div class="captcha-row">
            <el-input v-model="loginForm.code" placeholder="请输入验证码" size="large">
              <template #prefix><el-icon><Key /></el-icon></template>
            </el-input>
            <img
              v-if="codeUrl"
              :src="codeUrl"
              class="captcha-img"
              title="点击刷新验证码"
              @click="getCode"
            />
          </div>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            size="large"
            style="width: 100%"
            :loading="loading"
            @click="handleLogin"
          >
            登 录
          </el-button>
        </el-form-item>
      </el-form>
      <div class="login-tip">默认账号：admin / admin123</div>
    </el-card>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import request from '@/utils/request'
import useUserStore from '@/store/user'

const router = useRouter()
const userStore = useUserStore()

const loginFormRef = ref()
const loading = ref(false)
const captchaEnabled = ref(true)
const codeUrl = ref('')

const loginForm = reactive({
  username: 'admin',
  password: 'admin123',
  code: '',
  uuid: ''
})

const rules = {
  username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  code: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
}

async function getCode() {
  try {
    const res = await request({ url: '/captchaImage', method: 'get', silent: true })
    captchaEnabled.value = res.captchaEnabled === undefined ? true : res.captchaEnabled
    if (captchaEnabled.value) {
      codeUrl.value = 'data:image/gif;base64,' + res.img
      loginForm.uuid = res.uuid
    }
  } catch (e) {
    // 验证码接口异常时允许无验证码登录
    captchaEnabled.value = false
  }
}

function handleLogin() {
  loginFormRef.value.validate((valid) => {
    if (!valid) {
      return
    }
    loading.value = true
    userStore
      .login(loginForm)
      .then(() => {
        ElMessage.success('登录成功')
        router.push('/')
      })
      .catch(() => {
        getCode()
        loginForm.code = ''
      })
      .finally(() => {
        loading.value = false
      })
  })
}

onMounted(() => {
  getCode()
})
</script>

<style scoped>
.login-container {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #1f2d3d 0%, #2b4a6f 100%);
}

.login-card {
  width: 400px;
  padding: 10px 10px 0;
  border-radius: 8px;
}

.login-header {
  text-align: center;
  margin-bottom: 24px;
}

.login-header h2 {
  margin: 0;
  color: #303133;
}

.login-header p {
  margin: 6px 0 0;
  color: #909399;
  font-size: 12px;
  letter-spacing: 1px;
}

.captcha-row {
  display: flex;
  width: 100%;
  gap: 10px;
}

.captcha-img {
  height: 40px;
  width: 120px;
  cursor: pointer;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
}

.login-tip {
  text-align: center;
  color: #c0c4cc;
  font-size: 12px;
  padding-bottom: 10px;
}
</style>
