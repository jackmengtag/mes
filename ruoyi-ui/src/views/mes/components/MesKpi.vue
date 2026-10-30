<template>
  <div class="mes-kpi-grid" :style="{ '--cols': cols }">
    <div v-for="item in items" :key="item.label" class="mes-kpi" :class="'is-' + (item.tone || 'default')">
      <div class="mes-kpi__label">{{ item.label }}</div>
      <div class="mes-kpi__value">
        <span class="num">{{ format(item.value) }}</span>
        <span v-if="item.unit" class="unit">{{ item.unit }}</span>
      </div>
      <div class="mes-kpi__foot">
        <span>{{ item.foot }}</span>
        <span v-if="item.delta" class="delta" :class="item.trend === 'down' ? 'down' : 'up'">{{ item.delta }}</span>
      </div>
    </div>
  </div>
</template>

<script setup name="MesKpi">
const props = defineProps({
  items: { type: Array, default: () => [] },
  cols: { type: Number, default: 4 }
})

function format(v) {
  if (v === null || v === undefined || v === '') return '-'
  if (typeof v === 'number') {
    return v.toLocaleString('zh-CN', { maximumFractionDigits: 1 })
  }
  return v
}
</script>

<style scoped>
.mes-kpi-grid {
  display: grid;
  grid-template-columns: repeat(var(--cols), minmax(0, 1fr));
  gap: 12px;
  margin-bottom: 14px;
}
.mes-kpi {
  background: #fff;
  border: 1px solid #d3dbe0;
  border-left: 3px solid #12495f;
  border-radius: 6px;
  padding: 12px 14px;
  min-height: 86px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.mes-kpi.is-ok { border-left-color: #1b7f55; }
.mes-kpi.is-warn { border-left-color: #b8790a; }
.mes-kpi.is-danger { border-left-color: #b23a2e; }
.mes-kpi.is-accent { border-left-color: #e07b1a; }
.mes-kpi__label { font-size: 13px; color: #5b6b74; }
.mes-kpi__value { display: flex; align-items: baseline; gap: 4px; }
.mes-kpi__value .num { font-size: 26px; font-weight: 600; color: #16242b; font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.mes-kpi__value .unit { font-size: 12px; color: #8a98a0; }
.mes-kpi__foot { display: flex; justify-content: space-between; font-size: 12px; color: #8a98a0; gap: 8px; }
.mes-kpi__foot .delta.up { color: #1b7f55; }
.mes-kpi__foot .delta.down { color: #b23a2e; }
@media (max-width: 1200px) {
  .mes-kpi-grid { grid-template-columns: repeat(2, minmax(0, 1fr)); }
}
</style>
