<template>
  <crud-page v-if="config" :key="config.path" :config="config" />
  <el-empty v-else description="未找到资源配置" />
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import CrudPage from './CrudPage.vue'
import { getResourceByPath } from '@/config/resources'

const route = useRoute()

// 根据当前路由匹配资源配置
const config = computed(() => {
  const key = (route.meta && route.meta.resourcePath) || route.path
  return getResourceByPath(key)
})
</script>
