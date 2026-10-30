/* ============================================================
   MES 制造与供应链协同平台 · 接口契约桩（API Stub）
   ------------------------------------------------------------
   约定：
   · 所有方法返回 Promise，延迟 120~260ms 模拟网络
   · 返回结构 { data } / { list, total } / { data, mock:true }
   · 字段名与 assets/mock.js 完全一致，接后端时仅替换实现，
     页面代码无需改动（详见交付说明中的接口契约）
   ============================================================ */

var API = (function () {
  function delay(value, ms) {
    return new Promise(function (resolve) {
      setTimeout(function () { resolve(value); }, ms || (120 + Math.round(Math.random() * 140)));
    });
  }
  function copy(v) { return JSON.parse(JSON.stringify(v)); }

  return {
    /* 总览 */
    getOverview: function () { return delay({ data: copy(DB.overview), fallback: true }); },
    getPlant: function () { return delay({ data: copy(DB.plant) }); },

    /* 计划 */
    getPlans: function () { return delay({ data: copy(DB.plans) }); },
    publishPlan: function (payload) {
      return delay({ data: Object.assign({ publishedAt: new Date().toISOString(), count: 6 }, payload || {}), mock: true }, 420);
    },
    /* 生产执行 */
    getProduction: function () { return delay({ data: copy(DB.production) }); },
    submitProductionReport: function (payload) {
      var qty = Number(payload && payload.qty) || 0;
      return delay({ data: Object.assign({ reportNo: 'RP-' + Date.now().toString().slice(-8), qty: qty }, payload || {}), mock: true }, 380);
    },
    /* 质量 */
    getQuality: function () { return delay({ data: copy(DB.quality) }); },
    judgeNonconformity: function (payload) {
      return delay({ data: Object.assign({ ncNo: payload && payload.no, judgedAt: new Date().toISOString() }, payload || {}), mock: true }, 360);
    },
    /* 设备 */
    getEquipment: function () { return delay({ data: copy(DB.equipment) }); },
    sendEquipmentCommand: function (payload) {
      return delay({ data: Object.assign({ commandId: 'CMD-' + Date.now().toString().slice(-6), accepted: true }, payload || {}), mock: true }, 320);
    },
    /* 物料 */
    getMaterial: function () { return delay({ data: copy(DB.material) }); },
    createPulling: function (payload) {
      return delay({ data: Object.assign({ no: 'PL-261025-' + (13 + Math.floor(Math.random() * 80)), status: '待拣货' }, payload || {}), mock: true }, 400);
    },
    /* 追溯 */
    getTraceIndex: function () { return delay({ list: copy(DB.trace.index), total: DB.trace.index.length, fallback: true }); },
    traceBySerial: function (serial) {
      var d = copy(DB.trace.bySerial);
      if (serial) d.serial = serial;
      return delay({ data: d, fallback: true }, 300);
    },
    traceByBatch: function (batch) {
      var d = copy(DB.trace.byBatch);
      if (batch) d.batch = batch;
      return delay({ data: d, fallback: true }, 300);
    },
    /* 看板 */
    getBoard: function () { return delay({ data: copy(DB.board) }); },
    /* 分析 */
    getAnalysis: function () { return delay({ data: copy(DB.analysis) }); },
    /* 基础数据 */
    getMasterdata: function () { return delay({ data: copy(DB.masterdata) }); },
    /* 系统集成 */
    getSystem: function () { return delay({ data: copy(DB.system) }); },
    retryMessage: function (payload) {
      return delay({ data: Object.assign({ retryAt: new Date().toISOString(), result: 'queued' }, payload || {}), mock: true }, 360);
    },

    /* ---------- 供应链协同（来源：爱达兴高铂尔业务方案） ---------- */
    getScm: function () { return delay({ data: copy(DB.scm) }); },
    getPurchase: function () { return delay({ data: copy(DB.purchase) }); },
    getSales: function () { return delay({ data: copy(DB.sales) }); },
    getInventory: function () { return delay({ data: copy(DB.inventory) }); },
    getCollab: function () { return delay({ data: copy(DB.collab) }); },
    getCosting: function () { return delay({ data: copy(DB.costing) }); },
    getManufacturing: function () { return delay({ data: copy(DB.manufacturing) }); },
    getU8C: function () { return delay({ data: copy(DB.u8c) }); },

    createPurchaseOrder: function (payload) {
      return delay({ data: Object.assign({ no: 'PO-261025-' + (5 + Math.floor(Math.random() * 90)), status: '已下单' }, payload || {}), mock: true }, 380);
    },
    auditSupplier: function (payload) {
      return delay({ data: Object.assign({ auditedAt: new Date().toISOString() }, payload || {}), mock: true }, 320);
    },
    receivePurchase: function (payload) {
      return delay({ data: Object.assign({ no: 'DH-261025-' + (7 + Math.floor(Math.random() * 80)), status: '待入库' }, payload || {}), mock: true }, 360);
    },
    createSalesOrder: function (payload) {
      return delay({ data: Object.assign({ no: 'SO-261025-' + (9 + Math.floor(Math.random() * 80)), status: '待发货' }, payload || {}), mock: true }, 380);
    },
    confirmSalesInvoice: function (payload) {
      return delay({ data: Object.assign({ invoicedAt: new Date().toISOString(), status: '已开票' }, payload || {}), mock: true }, 340);
    },
    createStockMovement: function (payload) {
      var type = payload && payload.type === 'out' ? 'CK' : 'RK';
      return delay({ data: Object.assign({ no: type + '-261025-' + (20 + Math.floor(Math.random() * 70)), status: '待审核' }, payload || {}), mock: true }, 360);
    },
    createTransfer: function (payload) {
      return delay({ data: Object.assign({ no: 'DB-261025-' + (5 + Math.floor(Math.random() * 80)), status: '已调拨' }, payload || {}), mock: true }, 340);
    },
    submitStocktake: function (payload) {
      return delay({ data: Object.assign({ no: 'PD-2610-' + (5 + Math.floor(Math.random() * 80)), status: '已提交' }, payload || {}), mock: true }, 340);
    },
    postCostVoucher: function (payload) {
      return delay({ data: Object.assign({ postedAt: new Date().toISOString(), u8cSynced: true }, payload || {}), mock: true }, 400);
    },
    createCostAdjustment: function (payload) {
      return delay({ data: Object.assign({ no: 'TZ-261025-003', status: '待审核' }, payload || {}), mock: true }, 340);
    },
    registerShipment: function (payload) {
      return delay({ data: Object.assign({ no: 'SH-261025-007', status: '已发货' }, payload || {}), mock: true }, 340);
    },
    submitSample: function (payload) {
      return delay({ data: Object.assign({ no: 'YP-261025-004', status: '待审核' }, payload || {}), mock: true }, 340);
    },
    reportDeliveryException: function (payload) {
      return delay({ data: Object.assign({ no: 'YC-261025-006', status: '处理中' }, payload || {}), mock: true }, 340);
    },
    createInstruction: function (payload) {
      return delay({ data: Object.assign({ no: 'SC-261025-019', status: '待排产' }, payload || {}), mock: true }, 380);
    },
    createDailyPlan: function (payload) {
      return delay({ data: Object.assign({ feedingNo: 'TL-261028-001', status: '已排程' }, payload || {}), mock: true }, 380);
    },
    submitBomChange: function (payload) {
      return delay({ data: Object.assign({ no: 'EC-261025-003', status: '待评审' }, payload || {}), mock: true }, 340);
    },
    syncU8CVoucher: function (payload) {
      return delay({ data: Object.assign({ syncedAt: new Date().toISOString(), status: '已同步' }, payload || {}), mock: true }, 420);
    }
  };
})();

window.API = API;
