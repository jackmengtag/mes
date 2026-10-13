import { createRouter, createWebHistory } from 'vue-router'
import { getToken } from '@/utils/auth'
import Layout from '@/layout/index.vue'
import resources from '@/config/resources'

// 由 resources.js 自动生成每个 MES 资源的路由
const resourceRoutes = resources.map((r) => ({
  path: r.path,
  name: r.perm,
  component: () => import('@/views/ResourceView.vue'),
  meta: { title: r.title, resourcePath: r.path, module: r.module }
}))

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/index.vue'),
    meta: { title: '登录' },
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/index.vue'),
        meta: { title: '首页看板', icon: 'Odometer' }
      },
      ...resourceRoutes
    ]
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/dashboard'
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior: () => ({ top: 0 })
})

const BASE_TITLE = 'MES 制造执行系统'

router.beforeEach((to, from, next) => {
  const token = getToken()
  if (!token && to.path !== '/login') {
    next({ path: '/login' })
  } else if (token && to.path === '/login') {
    next({ path: '/' })
  } else {
    next()
  }
  document.title = to.meta && to.meta.title ? `${to.meta.title} - ${BASE_TITLE}` : BASE_TITLE
})

export default router
