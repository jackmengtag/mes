/**
 * MES 资源配置（由后端 ruoyi-mes 的 controller / domain 自动梳理生成）
 *
 * 每个资源配置结构：
 * {
 *   module:       业务模块编码，如 md
 *   moduleName:   模块中文名，如 主数据管理
 *   path:         接口及路由基础路径，如 /mes/md/material
 *   perm:         权限标识前缀，如 mes:md:material（默认由 path 推导）
 *   title:        资源中文标题，如 物料主数据
 *   columns:      表格列 [{ prop, label }]
 *   form:         表单字段 [{ prop, label, type, options }]
 *                 type 支持 input / textarea / number / date / select
 * }
 *
 * 权限标识规则：mes:{biz}:{entity}:{list|query|add|edit|remove|export}
 */

// ------------------------- 字段构造器 -------------------------
const col = (prop, label) => ({ prop, label })
const input = (prop, label) => ({ prop, label, type: 'input' })
const textarea = (prop, label) => ({ prop, label, type: 'textarea' })
const number = (prop, label) => ({ prop, label, type: 'number' })
const date = (prop, label) => ({ prop, label, type: 'date' })
const select = (prop, label, options) => ({ prop, label, type: 'select', options })

// ------------------------- 公共选项 -------------------------
const YES_NO = [
  { value: 'Y', label: '是' },
  { value: 'N', label: '否' }
]
const STATUS = [
  { value: '0', label: '正常' },
  { value: '1', label: '停用' }
]
const DOC_STATUS = [
  { value: 'DRAFT', label: '草稿' },
  { value: 'PENDING_APPROVAL', label: '待审批' },
  { value: 'PUBLISHED', label: '已发布' },
  { value: 'INVALID', label: '已失效' }
]
const WORK_ORDER_STATUS = [
  { value: 'CREATED', label: '新建' },
  { value: 'AUDITED', label: '已审核' },
  { value: 'SCHEDULED', label: '已排产' },
  { value: 'RELEASED', label: '已下达' },
  { value: 'WAIT_MATERIAL', label: '待料' },
  { value: 'EXECUTING', label: '执行中' },
  { value: 'PAUSED', label: '暂停' },
  { value: 'ABNORMAL', label: '异常' },
  { value: 'PARTIAL', label: '部分完成' },
  { value: 'COMPLETED', label: '已完成' },
  { value: 'CLOSED', label: '已关闭' }
]
const OPERATION_STATUS = [
  { value: 'NOT_STARTED', label: '未开始' },
  { value: 'DISPATCHED', label: '已派工' },
  { value: 'EXECUTING', label: '执行中' },
  { value: 'WAIT_INSPECT', label: '待检验' },
  { value: 'COMPLETED', label: '已完成' },
  { value: 'PAUSED', label: '暂停' },
  { value: 'ABNORMAL', label: '异常' },
  { value: 'CANCELLED', label: '已取消' }
]
const KIT_STATUS = [
  { value: 'NOT_CALCULATED', label: '未计算' },
  { value: 'LACK', label: '缺料' },
  { value: 'PARTIAL', label: '部分齐套' },
  { value: 'KITTED', label: '已齐套' },
  { value: 'DELIVERED', label: '已配送' },
  { value: 'PARTIAL_CONSUMED', label: '部分消耗' },
  { value: 'CONSUMED', label: '已消耗' }
]
const PLAN_STATUS = [
  { value: 'CREATED', label: '新建' },
  { value: 'SCHEDULED', label: '已排程' },
  { value: 'RELEASED', label: '已下达' },
  { value: 'CLOSED', label: '已关闭' }
]
const SCHEDULE_STATUS = [
  { value: 'PENDING', label: '待排程' },
  { value: 'SCHEDULED', label: '已排程' },
  { value: 'EXECUTING', label: '执行中' },
  { value: 'COMPLETED', label: '已完成' }
]
const DEMAND_STATUS = [
  { value: 'CONFIRMED', label: '已确认' },
  { value: 'DECOMPOSED', label: '已分解' },
  { value: 'PRODUCING', label: '生产中' },
  { value: 'PARTIAL', label: '部分完成' },
  { value: 'COMPLETED', label: '已完成' },
  { value: 'CLOSED', label: '已关闭' },
  { value: 'VALIDATION_FAILED', label: '校验失败' }
]
const INSPECTION_TYPE = [
  { value: 'IQC', label: '来料检验' },
  { value: 'FIRST', label: '首件检验' },
  { value: 'PATROL', label: '巡检' },
  { value: 'FQC', label: '完工检验' }
]
const INSPECTION_RESULT = [
  { value: 'PASS', label: '合格' },
  { value: 'FAIL', label: '不合格' },
  { value: 'PENDING', label: '待判定' }
]
const NONCONFORMITY_STATUS = [
  { value: 'REGISTERED', label: '已登记' },
  { value: 'ISOLATED', label: '已隔离' },
  { value: 'WAIT_DISPOSITION', label: '待判定' },
  { value: 'REWORKING', label: '返工中' },
  { value: 'REPAIRING', label: '返修中' },
  { value: 'SCRAPPED', label: '已报废' },
  { value: 'CONCESSION', label: '让步接收' },
  { value: 'WAIT_REINSPECT', label: '待复检' },
  { value: 'CLOSED', label: '已关闭' }
]
const DISPOSITION_TYPE = [
  { value: 'REWORK', label: '返工' },
  { value: 'REPAIR', label: '返修' },
  { value: 'SCRAP', label: '报废' },
  { value: 'CONCESSION', label: '让步接收' },
  { value: 'RETURN_SUPPLIER', label: '退供应商' }
]
const EQUIPMENT_STATUS = [
  { value: 'RUNNING', label: '运行' },
  { value: 'STANDBY', label: '待机' },
  { value: 'STOPPED', label: '停机' },
  { value: 'FAULT', label: '故障' },
  { value: 'MAINTENANCE', label: '维护' },
  { value: 'OFFLINE', label: '离线' }
]
const PROCESS_RESULT = [
  { value: 'SUCCESS', label: '成功' },
  { value: 'RETRYABLE', label: '可重试' },
  { value: 'NOT_RETRYABLE', label: '不可重试' },
  { value: 'BUSINESS_CONFLICT', label: '业务冲突' }
]
const SERIAL_STATUS = [
  { value: 'CREATED', label: '已创建' },
  { value: 'IN_PROCESS', label: '在制' },
  { value: 'WAIT_INSPECT', label: '待检验' },
  { value: 'QUALIFIED', label: '合格' },
  { value: 'UNQUALIFIED', label: '不合格' },
  { value: 'SCRAPPED', label: '已报废' },
  { value: 'IN_STOCK', label: '已入库' },
  { value: 'SHIPPED', label: '已发货' }
]
const SYNC_STATUS = [
  { value: 'PENDING', label: '待同步' },
  { value: 'SUCCESS', label: '成功' },
  { value: 'FAILED', label: '失败' },
  { value: 'DEAD_LETTER', label: '死信' },
  { value: 'MANUAL', label: '人工处理' }
]
const MESSAGE_STATUS = [
  { value: 'CREATED', label: '已创建' },
  { value: 'VALIDATED', label: '已校验' },
  { value: 'SENT', label: '已发送' },
  { value: 'ACCEPTED', label: '已受理' },
  { value: 'PROCESSED', label: '已处理' },
  { value: 'CONFIRMED', label: '已确认' },
  { value: 'VALIDATION_FAILED', label: '校验失败' },
  { value: 'RETRYING', label: '重试中' },
  { value: 'DEAD_LETTER', label: '死信' },
  { value: 'MANUAL_PROCESSING', label: '人工处理中' },
  { value: 'CANCELLED', label: '已取消' }
]
const PRIORITY = [
  { value: 'HIGH', label: '高' },
  { value: 'MEDIUM', label: '中' },
  { value: 'LOW', label: '低' }
]
const MATERIAL_TYPE = [
  { value: 'RAW', label: '原材料' },
  { value: 'SEMI', label: '半成品' },
  { value: 'FINISHED', label: '成品' },
  { value: 'AUXILIARY', label: '辅料' }
]
const PRODUCT_TYPE = [
  { value: 'FINISHED', label: '成品' },
  { value: 'SEMI', label: '半成品' }
]
const DATA_TYPE = [
  { value: 'INT', label: '整型' },
  { value: 'FLOAT', label: '浮点' },
  { value: 'BOOL', label: '布尔' },
  { value: 'STRING', label: '字符串' }
]
const COLLECT_MODE = [
  { value: 'AUTO', label: '自动采集' },
  { value: 'MANUAL', label: '手工录入' },
  { value: 'NONE', label: '不采集' }
]
const MAINTENANCE_TYPE = [
  { value: 'PLAN', label: '计划维护' },
  { value: 'FAULT', label: '故障维修' },
  { value: 'SPOT', label: '点检维护' }
]
const MAINTENANCE_STATUS = [
  { value: 'PENDING', label: '待处理' },
  { value: 'PROCESSING', label: '处理中' },
  { value: 'COMPLETED', label: '已完成' },
  { value: 'CLOSED', label: '已关闭' }
]
const ALARM_LEVEL = [
  { value: 'INFO', label: '提示' },
  { value: 'WARN', label: '警告' },
  { value: 'ERROR', label: '严重' }
]
const CHECK_RESULT = [
  { value: 'OK', label: '正常' },
  { value: 'NG', label: '异常' }
]
const WORK_TYPE = [
  { value: 'LABOR', label: '人工工时' },
  { value: 'MACHINE', label: '机器工时' }
]
const COMMAND_TYPE = [
  { value: 'START', label: '启动' },
  { value: 'STOP', label: '停止' },
  { value: 'RESET', label: '复位' },
  { value: 'DOWNLOAD', label: '程序下发' }
]
const COMMAND_STATUS = [
  { value: 'CREATED', label: '待下发' },
  { value: 'SENT', label: '已下发' },
  { value: 'SUCCESS', label: '成功' },
  { value: 'FAILED', label: '失败' }
]
const REWORK_STATUS = [
  { value: 'CREATED', label: '新建' },
  { value: 'EXECUTING', label: '返工中' },
  { value: 'COMPLETED', label: '已完成' },
  { value: 'CANCELLED', label: '已取消' }
]
const HANDLE_STATUS = [
  { value: 'PENDING', label: '待处理' },
  { value: 'PROCESSING', label: '处理中' },
  { value: 'RESOLVED', label: '已解决' },
  { value: 'CLOSED', label: '已关闭' }
]
const SEVERITY = [
  { value: 'LOW', label: '轻微' },
  { value: 'MEDIUM', label: '一般' },
  { value: 'HIGH', label: '严重' },
  { value: 'CRITICAL', label: '致命' }
]
const DIRECTION = [
  { value: 'IN', label: '接收' },
  { value: 'OUT', label: '发送' }
]
const VERSION_BIZ_TYPE = [
  { value: 'PRODUCT', label: '产品' },
  { value: 'BOM', label: 'BOM' },
  { value: 'ROUTE', label: '工艺' }
]
const RECONCILE_STATUS = [
  { value: 'PENDING', label: '待执行' },
  { value: 'RUNNING', label: '执行中' },
  { value: 'SUCCESS', label: '成功' },
  { value: 'FAILED', label: '失败' }
]
const DEFECT_CATEGORY = [
  { value: 'APPEARANCE', label: '外观' },
  { value: 'DIMENSION', label: '尺寸' },
  { value: 'FUNCTION', label: '功能' },
  { value: 'MATERIAL', label: '材质' },
  { value: 'OTHER', label: '其他' }
]
const WAREHOUSE_CONFIRM_STATUS = [
  { value: 'PENDING', label: '待确认' },
  { value: 'CONFIRMED', label: '已确认' },
  { value: 'REJECTED', label: '已驳回' }
]

// ------------------------- 资源清单 -------------------------
const RESOURCES = [
  // ======================= 主数据管理 md =======================
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/material',
    title: '物料主数据',
    columns: [
      col('materialCode', '物料编码'),
      col('materialName', '物料名称'),
      col('unit', '计量单位'),
      col('materialType', '物料类型'),
      col('batchFlag', '批次管理标识'),
      col('serialFlag', '序列号管理标识'),
      col('shelfLife', '保质期(天)'),
      col('keyFlag', '是否关键物料'),
      col('inspectFlag', '是否需要检验'),
      col('substituteFlag', '是否允许替代'),
      col('status', '状态')
    ],
    form: [
      input('materialCode', '物料编码'),
      input('materialName', '物料名称'),
      input('unit', '计量单位'),
      select('materialType', '物料类型', MATERIAL_TYPE),
      select('batchFlag', '批次管理标识', YES_NO),
      select('serialFlag', '序列号管理标识', YES_NO),
      number('shelfLife', '保质期(天)'),
      select('keyFlag', '是否关键物料', YES_NO),
      select('inspectFlag', '是否需要检验', YES_NO),
      select('substituteFlag', '是否允许替代', YES_NO),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/product',
    title: '产品主数据',
    columns: [
      col('productCode', '产品编码'),
      col('productName', '产品名称'),
      col('productType', '产品类型'),
      col('serialFlag', '序列号管理标识'),
      col('defaultBomVersionId', '默认BOM版本'),
      col('defaultRouteVersionId', '默认工艺版本'),
      col('status', '状态')
    ],
    form: [
      input('productCode', '产品编码'),
      input('productName', '产品名称'),
      select('productType', '产品类型', PRODUCT_TYPE),
      select('serialFlag', '序列号管理标识', YES_NO),
      number('defaultBomVersionId', '默认BOM版本'),
      number('defaultRouteVersionId', '默认工艺版本'),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/productVersion',
    title: '产品版本',
    columns: [
      col('productId', '产品ID'),
      col('productCode', '产品编码'),
      col('versionNo', '版本号'),
      col('bomVersionId', 'BOM版本'),
      col('routeVersionId', '工艺版本'),
      col('effectiveTime', '生效时间'),
      col('expireTime', '失效时间'),
      col('docStatus', '版本状态'),
      col('status', '状态')
    ],
    form: [
      number('productId', '产品ID'),
      input('productCode', '产品编码'),
      input('versionNo', '版本号'),
      number('bomVersionId', 'BOM版本'),
      number('routeVersionId', '工艺版本'),
      date('effectiveTime', '生效时间'),
      date('expireTime', '失效时间'),
      select('docStatus', '版本状态', DOC_STATUS),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/bom',
    title: 'BOM主数据',
    columns: [
      col('bomCode', 'BOM编码'),
      col('productId', '产品ID'),
      col('productCode', '产品编码'),
      col('versionNo', '版本号'),
      col('docStatus', '版本状态'),
      col('status', '状态')
    ],
    form: [
      input('bomCode', 'BOM编码'),
      number('productId', '产品ID'),
      input('productCode', '产品编码'),
      input('versionNo', '版本号'),
      select('docStatus', '版本状态', DOC_STATUS),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/bomLine',
    title: 'BOM明细',
    columns: [
      col('bomId', 'BOM ID'),
      col('materialId', '物料ID'),
      col('materialCode', '物料编码'),
      col('materialName', '物料名称'),
      col('quantity', '用量'),
      col('lossRate', '损耗率'),
      col('feedingOperationCode', '投料工序'),
      col('keyFlag', '是否关键物料'),
      col('batchBindFlag', '批次绑定要求'),
      col('substituteFlag', '是否允许替代'),
      col('substituteMaterialCode', '替代料编码')
    ],
    form: [
      number('bomId', 'BOM ID'),
      number('materialId', '物料ID'),
      input('materialCode', '物料编码'),
      input('materialName', '物料名称'),
      number('quantity', '用量'),
      number('lossRate', '损耗率'),
      input('feedingOperationCode', '投料工序'),
      select('keyFlag', '是否关键物料', YES_NO),
      select('batchBindFlag', '批次绑定要求', YES_NO),
      select('substituteFlag', '是否允许替代', YES_NO),
      input('substituteMaterialCode', '替代料编码')
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/route',
    title: '工艺路线',
    columns: [
      col('routeCode', '工艺编码'),
      col('routeName', '工艺名称'),
      col('productId', '产品ID'),
      col('productCode', '产品编码'),
      col('versionNo', '版本号'),
      col('docStatus', '版本状态'),
      col('status', '状态')
    ],
    form: [
      input('routeCode', '工艺编码'),
      input('routeName', '工艺名称'),
      number('productId', '产品ID'),
      input('productCode', '产品编码'),
      input('versionNo', '版本号'),
      select('docStatus', '版本状态', DOC_STATUS),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/routeOperation',
    title: '工艺工序',
    columns: [
      col('routeId', '工艺路线ID'),
      col('operationCode', '工序编码'),
      col('operationName', '工序名称'),
      col('sequenceNo', '工序顺序号'),
      col('workCenterId', '工作中心'),
      col('equipmentType', '设备类型要求'),
      col('skillRequirement', '技能要求'),
      col('forceInspect', '是否强制检验'),
      col('inspectionPlanId', '检验方案'),
      col('standardHours', '标准工时'),
      col('status', '状态')
    ],
    form: [
      number('routeId', '工艺路线ID'),
      input('operationCode', '工序编码'),
      input('operationName', '工序名称'),
      number('sequenceNo', '工序顺序号'),
      number('workCenterId', '工作中心'),
      input('equipmentType', '设备类型要求'),
      input('skillRequirement', '技能要求'),
      select('forceInspect', '是否强制检验', YES_NO),
      number('inspectionPlanId', '检验方案'),
      number('standardHours', '标准工时'),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/inspectionItem',
    title: '检验项目',
    columns: [
      col('itemCode', '项目编码'),
      col('itemName', '项目名称'),
      col('unit', '单位'),
      col('lowerLimit', '下限'),
      col('upperLimit', '上限'),
      col('standardValue', '标准值'),
      col('status', '状态')
    ],
    form: [
      input('itemCode', '项目编码'),
      input('itemName', '项目名称'),
      input('unit', '单位'),
      number('lowerLimit', '下限'),
      number('upperLimit', '上限'),
      number('standardValue', '标准值'),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/inspectionPlan',
    title: '检验方案',
    columns: [
      col('planCode', '方案编码'),
      col('planName', '方案名称'),
      col('inspectionType', '检验类型'),
      col('productId', '产品ID'),
      col('operationCode', '工序编码'),
      col('versionNo', '版本号'),
      col('docStatus', '版本状态'),
      col('status', '状态')
    ],
    form: [
      input('planCode', '方案编码'),
      input('planName', '方案名称'),
      select('inspectionType', '检验类型', INSPECTION_TYPE),
      number('productId', '产品ID'),
      input('operationCode', '工序编码'),
      input('versionNo', '版本号'),
      select('docStatus', '版本状态', DOC_STATUS),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/inspectionPlanItem',
    title: '检验方案明细',
    columns: [
      col('planId', '方案ID'),
      col('itemId', '检验项目ID'),
      col('itemCode', '项目编码'),
      col('itemName', '项目名称'),
      col('lowerLimit', '下限'),
      col('upperLimit', '上限'),
      col('unit', '单位'),
      col('samplingRule', '抽样规则')
    ],
    form: [
      number('planId', '方案ID'),
      number('itemId', '检验项目ID'),
      input('itemCode', '项目编码'),
      input('itemName', '项目名称'),
      number('lowerLimit', '下限'),
      number('upperLimit', '上限'),
      input('unit', '单位'),
      input('samplingRule', '抽样规则')
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/workCenter',
    title: '工作中心',
    columns: [
      col('workCenterCode', '工作中心编码'),
      col('workCenterName', '工作中心名称'),
      col('lineId', '所属产线'),
      col('capacity', '产能'),
      col('status', '状态')
    ],
    form: [
      input('workCenterCode', '工作中心编码'),
      input('workCenterName', '工作中心名称'),
      number('lineId', '所属产线'),
      number('capacity', '产能'),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/line',
    title: '产线',
    columns: [
      col('lineCode', '产线编码'),
      col('lineName', '产线名称'),
      col('plantCode', '工厂编码'),
      col('status', '状态')
    ],
    form: [
      input('lineCode', '产线编码'),
      input('lineName', '产线名称'),
      input('plantCode', '工厂编码'),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/station',
    title: '工位',
    columns: [
      col('stationCode', '工位编码'),
      col('stationName', '工位名称'),
      col('lineId', '所属产线'),
      col('workCenterId', '所属工作中心'),
      col('status', '状态')
    ],
    form: [
      input('stationCode', '工位编码'),
      input('stationName', '工位名称'),
      number('lineId', '所属产线'),
      number('workCenterId', '所属工作中心'),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/equipment',
    title: '设备',
    columns: [
      col('equipmentCode', '设备编码'),
      col('equipmentName', '设备名称'),
      col('equipmentType', '设备类型'),
      col('workCenterId', '归属工作中心'),
      col('collectMode', '采集方式'),
      col('status', '状态')
    ],
    form: [
      input('equipmentCode', '设备编码'),
      input('equipmentName', '设备名称'),
      input('equipmentType', '设备类型'),
      number('workCenterId', '归属工作中心'),
      select('collectMode', '采集方式', COLLECT_MODE),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/equipmentPoint',
    title: '设备点位',
    columns: [
      col('equipmentId', '设备ID'),
      col('pointCode', '点位编码'),
      col('pointName', '点位名称'),
      col('protocol', '通信协议'),
      col('address', '点位地址'),
      col('dataType', '数据类型'),
      col('unit', '单位'),
      col('changeReason', '变更原因')
    ],
    form: [
      number('equipmentId', '设备ID'),
      input('pointCode', '点位编码'),
      input('pointName', '点位名称'),
      input('protocol', '通信协议'),
      input('address', '点位地址'),
      select('dataType', '数据类型', DATA_TYPE),
      input('unit', '单位'),
      textarea('changeReason', '变更原因')
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/employee',
    title: '员工',
    columns: [
      col('empNo', '员工工号'),
      col('empName', '员工姓名'),
      col('postName', '岗位'),
      col('skills', '技能'),
      col('teamId', '所属班组'),
      col('shiftId', '所属班次'),
      col('userId', '关联系统用户'),
      col('status', '状态')
    ],
    form: [
      input('empNo', '员工工号'),
      input('empName', '员工姓名'),
      input('postName', '岗位'),
      textarea('skills', '技能'),
      number('teamId', '所属班组'),
      number('shiftId', '所属班次'),
      number('userId', '关联系统用户'),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/team',
    title: '班组',
    columns: [
      col('teamCode', '班组编码'),
      col('teamName', '班组名称'),
      col('leaderNo', '班组长工号'),
      col('status', '状态')
    ],
    form: [
      input('teamCode', '班组编码'),
      input('teamName', '班组名称'),
      input('leaderNo', '班组长工号'),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/shift',
    title: '班次',
    columns: [
      col('shiftCode', '班次编码'),
      col('shiftName', '班次名称'),
      col('startTime', '开始时间'),
      col('endTime', '结束时间'),
      col('status', '状态')
    ],
    form: [
      input('shiftCode', '班次编码'),
      input('shiftName', '班次名称'),
      input('startTime', '开始时间'),
      input('endTime', '结束时间'),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/defectCode',
    title: '缺陷代码',
    columns: [
      col('defectCode', '缺陷代码'),
      col('defectName', '缺陷名称'),
      col('defectCategory', '缺陷类别'),
      col('respDept', '责任部门'),
      col('defaultDisposition', '默认处理方式'),
      col('status', '状态')
    ],
    form: [
      input('defectCode', '缺陷代码'),
      input('defectName', '缺陷名称'),
      select('defectCategory', '缺陷类别', DEFECT_CATEGORY),
      input('respDept', '责任部门'),
      select('defaultDisposition', '默认处理方式', DISPOSITION_TYPE),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'md',
    moduleName: '主数据管理',
    path: '/mes/md/versionApproval',
    title: '版本审批',
    columns: [
      col('bizType', '业务类型'),
      col('bizId', '业务ID'),
      col('versionNo', '版本号'),
      col('docStatus', '版本状态'),
      col('changeReason', '变更原因'),
      col('changeBy', '变更人'),
      col('changeTime', '变更时间')
    ],
    form: [
      select('bizType', '业务类型', VERSION_BIZ_TYPE),
      number('bizId', '业务ID'),
      input('versionNo', '版本号'),
      select('docStatus', '版本状态', DOC_STATUS),
      textarea('changeReason', '变更原因'),
      input('changeBy', '变更人'),
      date('changeTime', '变更时间')
    ]
  },

  // ======================= 需求管理 om =======================
  {
    module: 'om',
    moduleName: '需求管理',
    path: '/mes/om/demand',
    title: '生产需求',
    columns: [
      col('demandNo', '需求单号'),
      col('sourceOrderNo', '来源订单号'),
      col('sourceOrderLineNo', '来源订单行号'),
      col('sourceSystem', '来源系统'),
      col('erpDemandNo', 'ERP需求号'),
      col('plantCode', '工厂编码'),
      col('productId', '产品ID'),
      col('productCode', '产品编码'),
      col('productVersion', '产品版本'),
      col('requiredQty', '需求数量'),
      col('dueDate', '交期'),
      col('priority', '优先级'),
      col('status', '状态'),
      col('idempotencyKey', '幂等键'),
      col('changeReason', '变更原因')
    ],
    form: [
      input('demandNo', '需求单号'),
      input('sourceOrderNo', '来源订单号'),
      input('sourceOrderLineNo', '来源订单行号'),
      input('sourceSystem', '来源系统'),
      input('erpDemandNo', 'ERP需求号'),
      input('plantCode', '工厂编码'),
      number('productId', '产品ID'),
      input('productCode', '产品编码'),
      input('productVersion', '产品版本'),
      number('requiredQty', '需求数量'),
      date('dueDate', '交期'),
      select('priority', '优先级', PRIORITY),
      select('status', '状态', DEMAND_STATUS),
      input('idempotencyKey', '幂等键'),
      textarea('changeReason', '变更原因')
    ]
  },

  // ======================= 计划排程 ps =======================
  {
    module: 'ps',
    moduleName: '计划排程',
    path: '/mes/ps/plan',
    title: '生产计划',
    columns: [
      col('planNo', '计划单号'),
      col('demandId', '需求ID'),
      col('demandNo', '需求单号'),
      col('productId', '产品ID'),
      col('productCode', '产品编码'),
      col('planQty', '计划数量'),
      col('plannedStartTime', '计划开始'),
      col('plannedEndTime', '计划结束'),
      col('lineId', '产线'),
      col('priority', '优先级'),
      col('status', '状态')
    ],
    form: [
      input('planNo', '计划单号'),
      number('demandId', '需求ID'),
      input('demandNo', '需求单号'),
      number('productId', '产品ID'),
      input('productCode', '产品编码'),
      number('planQty', '计划数量'),
      date('plannedStartTime', '计划开始'),
      date('plannedEndTime', '计划结束'),
      number('lineId', '产线'),
      select('priority', '优先级', PRIORITY),
      select('status', '状态', PLAN_STATUS)
    ]
  },
  {
    module: 'ps',
    moduleName: '计划排程',
    path: '/mes/ps/scheduleTask',
    title: '排程任务',
    columns: [
      col('planId', '计划ID'),
      col('workOrderId', '工单ID'),
      col('lineId', '产线'),
      col('sequenceNo', '排序号'),
      col('plannedStartTime', '计划开始'),
      col('plannedEndTime', '计划结束'),
      col('scheduleType', '排程方式'),
      col('conflictFlag', '是否冲突'),
      col('conflictReason', '冲突原因'),
      col('adjustReason', '调整原因'),
      col('status', '状态')
    ],
    form: [
      number('planId', '计划ID'),
      number('workOrderId', '工单ID'),
      number('lineId', '产线'),
      number('sequenceNo', '排序号'),
      date('plannedStartTime', '计划开始'),
      date('plannedEndTime', '计划结束'),
      select('scheduleType', '排程方式', [
        { value: 'FORWARD', label: '正排' },
        { value: 'BACKWARD', label: '倒排' }
      ]),
      select('conflictFlag', '是否冲突', YES_NO),
      textarea('conflictReason', '冲突原因'),
      textarea('adjustReason', '调整原因'),
      select('status', '状态', SCHEDULE_STATUS)
    ]
  },

  // ======================= 生产工单 wo =======================
  {
    module: 'wo',
    moduleName: '生产工单',
    path: '/mes/wo/order',
    title: '生产工单',
    columns: [
      col('orderNo', '工单号'),
      col('sourceOrderNo', '来源订单号'),
      col('erpWorkOrderNo', 'ERP工单号'),
      col('demandId', '需求ID'),
      col('parentOrderId', '父工单ID'),
      col('splitReason', '拆分原因'),
      col('productId', '产品ID'),
      col('productCode', '产品编码'),
      col('productVersionId', '固化产品版本'),
      col('routeVersionId', '固化工艺版本'),
      col('bomVersionId', '固化BOM版本'),
      col('inspectionPlanVersionId', '固化检验方案版本'),
      col('lineId', '产线'),
      col('priority', '优先级'),
      col('plannedQty', '计划数量'),
      col('completedQty', '完成数量'),
      col('qualifiedQty', '合格数量'),
      col('scrapQty', '报废数量'),
      col('kitStatus', '齐套状态'),
      col('plannedStartTime', '计划开始'),
      col('plannedEndTime', '计划结束'),
      col('actualStartTime', '实际开始'),
      col('actualEndTime', '实际结束'),
      col('pauseReason', '暂停原因'),
      col('status', '工单状态')
    ],
    form: [
      input('orderNo', '工单号'),
      input('sourceOrderNo', '来源订单号'),
      input('erpWorkOrderNo', 'ERP工单号'),
      number('demandId', '需求ID'),
      number('parentOrderId', '父工单ID'),
      textarea('splitReason', '拆分原因'),
      number('productId', '产品ID'),
      input('productCode', '产品编码'),
      number('productVersionId', '固化产品版本'),
      number('routeVersionId', '固化工艺版本'),
      number('bomVersionId', '固化BOM版本'),
      number('inspectionPlanVersionId', '固化检验方案版本'),
      number('lineId', '产线'),
      select('priority', '优先级', PRIORITY),
      number('plannedQty', '计划数量'),
      number('completedQty', '完成数量'),
      number('qualifiedQty', '合格数量'),
      number('scrapQty', '报废数量'),
      select('kitStatus', '齐套状态', KIT_STATUS),
      date('plannedStartTime', '计划开始'),
      date('plannedEndTime', '计划结束'),
      date('actualStartTime', '实际开始'),
      date('actualEndTime', '实际结束'),
      textarea('pauseReason', '暂停原因'),
      select('status', '工单状态', WORK_ORDER_STATUS)
    ]
  },
  {
    module: 'wo',
    moduleName: '生产工单',
    path: '/mes/wo/operationTask',
    title: '工序任务',
    columns: [
      col('productionOrderId', '所属工单'),
      col('orderNo', '工单号'),
      col('operationCode', '工序编码'),
      col('operationName', '工序名称'),
      col('sequenceNo', '工序顺序号'),
      col('workCenterId', '工作中心'),
      col('requiredEquipmentType', '设备类型要求'),
      col('plannedQty', '计划数量'),
      col('completedQty', '完成数量'),
      col('qualifiedQty', '合格数量'),
      col('unqualifiedQty', '不合格数量'),
      col('scrapQty', '报废数量'),
      col('forceInspect', '是否强制检验'),
      col('dispatchTeamId', '派工班组'),
      col('operatorNo', '执行人'),
      col('status', '工序状态')
    ],
    form: [
      number('productionOrderId', '所属工单'),
      input('orderNo', '工单号'),
      input('operationCode', '工序编码'),
      input('operationName', '工序名称'),
      number('sequenceNo', '工序顺序号'),
      number('workCenterId', '工作中心'),
      input('requiredEquipmentType', '设备类型要求'),
      number('plannedQty', '计划数量'),
      number('completedQty', '完成数量'),
      number('qualifiedQty', '合格数量'),
      number('unqualifiedQty', '不合格数量'),
      number('scrapQty', '报废数量'),
      select('forceInspect', '是否强制检验', YES_NO),
      number('dispatchTeamId', '派工班组'),
      input('operatorNo', '执行人'),
      select('status', '工序状态', OPERATION_STATUS)
    ]
  },
  {
    module: 'wo',
    moduleName: '生产工单',
    path: '/mes/wo/reworkOrder',
    title: '返工单',
    columns: [
      col('reworkNo', '返工单号'),
      col('originOrderId', '原工单ID'),
      col('originOrderNo', '原工单号'),
      col('nonconformityId', '不合格记录ID'),
      col('serialNo', '产品序列号'),
      col('operationCode', '返工工序'),
      col('reworkQty', '返工数量'),
      col('reason', '返工原因'),
      col('status', '状态')
    ],
    form: [
      input('reworkNo', '返工单号'),
      number('originOrderId', '原工单ID'),
      input('originOrderNo', '原工单号'),
      number('nonconformityId', '不合格记录ID'),
      input('serialNo', '产品序列号'),
      input('operationCode', '返工工序'),
      number('reworkQty', '返工数量'),
      textarea('reason', '返工原因'),
      select('status', '状态', REWORK_STATUS)
    ]
  },

  // ======================= 生产执行 ex =======================
  {
    module: 'ex',
    moduleName: '生产执行',
    path: '/mes/ex/report',
    title: '生产报工',
    columns: [
      col('reportNo', '报工单号'),
      col('productionOrderId', '所属工单'),
      col('orderNo', '工单号'),
      col('operationTaskId', '工序任务ID'),
      col('operationCode', '工序编码'),
      col('serialNo', '产品序列号'),
      col('qualifiedQty', '合格数量'),
      col('unqualifiedQty', '不合格数量'),
      col('scrapQty', '报废数量'),
      col('laborMinutes', '人工工时(分)'),
      col('machineMinutes', '机器工时(分)'),
      col('equipmentId', '设备ID'),
      col('operatorNo', '操作员'),
      col('shiftCode', '班次'),
      col('reportedAt', '报工时间'),
      col('idempotencyKey', '幂等键'),
      col('syncStatus', 'ERP同步状态')
    ],
    form: [
      input('reportNo', '报工单号'),
      number('productionOrderId', '所属工单'),
      input('orderNo', '工单号'),
      number('operationTaskId', '工序任务ID'),
      input('operationCode', '工序编码'),
      input('serialNo', '产品序列号'),
      number('qualifiedQty', '合格数量'),
      number('unqualifiedQty', '不合格数量'),
      number('scrapQty', '报废数量'),
      number('laborMinutes', '人工工时(分)'),
      number('machineMinutes', '机器工时(分)'),
      number('equipmentId', '设备ID'),
      input('operatorNo', '操作员'),
      input('shiftCode', '班次'),
      date('reportedAt', '报工时间'),
      input('idempotencyKey', '幂等键'),
      select('syncStatus', 'ERP同步状态', SYNC_STATUS)
    ]
  },
  {
    module: 'ex',
    moduleName: '生产执行',
    path: '/mes/ex/serial',
    title: '产品序列号',
    columns: [
      col('serialNo', '序列号'),
      col('productId', '产品ID'),
      col('productCode', '产品编码'),
      col('productionOrderId', '所属工单'),
      col('currentOperation', '当前工序'),
      col('currentStatus', '当前状态'),
      col('qualityStatus', '质量状态'),
      col('warehouseStatus', '库存状态'),
      col('scrapFlag', '报废标识')
    ],
    form: [
      input('serialNo', '序列号'),
      number('productId', '产品ID'),
      input('productCode', '产品编码'),
      number('productionOrderId', '所属工单'),
      input('currentOperation', '当前工序'),
      select('currentStatus', '当前状态', SERIAL_STATUS),
      select('qualityStatus', '质量状态', [
        { value: 'QUALIFIED', label: '合格' },
        { value: 'UNQUALIFIED', label: '不合格' }
      ]),
      select('warehouseStatus', '库存状态', [
        { value: 'IN_STOCK', label: '在库' },
        { value: 'OUT', label: '出库' }
      ]),
      select('scrapFlag', '报废标识', YES_NO)
    ]
  },
  {
    module: 'ex',
    moduleName: '生产执行',
    path: '/mes/ex/wip',
    title: '在制品',
    columns: [
      col('productionOrderId', '工单ID'),
      col('orderNo', '工单号'),
      col('operationCode', '工序编码'),
      col('wipQty', '在制数量'),
      col('stayMinutes', '停留时长(分)'),
      col('statTime', '统计时间')
    ],
    form: [
      number('productionOrderId', '工单ID'),
      input('orderNo', '工单号'),
      input('operationCode', '工序编码'),
      number('wipQty', '在制数量'),
      number('stayMinutes', '停留时长(分)'),
      date('statTime', '统计时间')
    ]
  },
  {
    module: 'ex',
    moduleName: '生产执行',
    path: '/mes/ex/workHour',
    title: '工时记录',
    columns: [
      col('productionOrderId', '工单ID'),
      col('operationTaskId', '工序任务ID'),
      col('workType', '工时类型'),
      col('minutes', '工时(分)'),
      col('operatorNo', '操作员'),
      col('recordTime', '记录时间')
    ],
    form: [
      number('productionOrderId', '工单ID'),
      number('operationTaskId', '工序任务ID'),
      select('workType', '工时类型', WORK_TYPE),
      number('minutes', '工时(分)'),
      input('operatorNo', '操作员'),
      date('recordTime', '记录时间')
    ]
  },
  {
    module: 'ex',
    moduleName: '生产执行',
    path: '/mes/ex/processParam',
    title: '过程参数',
    columns: [
      col('productionOrderId', '工单ID'),
      col('operationTaskId', '工序任务ID'),
      col('serialNo', '序列号'),
      col('equipmentId', '设备ID'),
      col('paramName', '参数名称'),
      col('paramValue', '参数值'),
      col('paramUnit', '单位'),
      col('lowerLimit', '下限'),
      col('upperLimit', '上限'),
      col('result', '判定结果'),
      col('collectedAt', '采集时间')
    ],
    form: [
      number('productionOrderId', '工单ID'),
      number('operationTaskId', '工序任务ID'),
      input('serialNo', '序列号'),
      number('equipmentId', '设备ID'),
      input('paramName', '参数名称'),
      input('paramValue', '参数值'),
      input('paramUnit', '单位'),
      number('lowerLimit', '下限'),
      number('upperLimit', '上限'),
      select('result', '判定结果', INSPECTION_RESULT),
      date('collectedAt', '采集时间')
    ]
  },
  {
    module: 'ex',
    moduleName: '生产执行',
    path: '/mes/ex/exceptionReport',
    title: '异常上报',
    columns: [
      col('exceptionNo', '异常单号'),
      col('exceptionType', '异常类型'),
      col('productionOrderId', '工单ID'),
      col('operationTaskId', '工序任务ID'),
      col('equipmentId', '设备ID'),
      col('serialNo', '序列号'),
      col('severity', '严重程度'),
      col('description', '异常描述'),
      col('reporterNo', '上报人'),
      col('reportedAt', '上报时间'),
      col('handlerNo', '处理人'),
      col('handleResult', '处理结果'),
      col('status', '状态')
    ],
    form: [
      input('exceptionNo', '异常单号'),
      input('exceptionType', '异常类型'),
      number('productionOrderId', '工单ID'),
      number('operationTaskId', '工序任务ID'),
      number('equipmentId', '设备ID'),
      input('serialNo', '序列号'),
      select('severity', '严重程度', SEVERITY),
      textarea('description', '异常描述'),
      input('reporterNo', '上报人'),
      date('reportedAt', '上报时间'),
      input('handlerNo', '处理人'),
      select('handleResult', '处理结果', [
        { value: 'RESOLVED', label: '已解决' },
        { value: 'UNRESOLVED', label: '未解决' }
      ]),
      select('status', '状态', HANDLE_STATUS)
    ]
  },

  // ======================= 物料管理 mt =======================
  {
    module: 'mt',
    moduleName: '物料管理',
    path: '/mes/mt/requirement',
    title: '物料需求',
    columns: [
      col('requirementNo', '需求单号'),
      col('productionOrderId', '工单ID'),
      col('orderNo', '工单号'),
      col('operationCode', '投料工序'),
      col('materialId', '物料ID'),
      col('materialCode', '物料编码'),
      col('materialName', '物料名称'),
      col('unit', '单位'),
      col('requiredQty', '需求数量'),
      col('allocatedQty', '分配数量'),
      col('pickedQty', '拣料数量'),
      col('consumedQty', '消耗数量'),
      col('lackQty', '缺料数量'),
      col('keyFlag', '是否关键物料'),
      col('kitStatus', '齐套状态'),
      col('status', '状态')
    ],
    form: [
      input('requirementNo', '需求单号'),
      number('productionOrderId', '工单ID'),
      input('orderNo', '工单号'),
      input('operationCode', '投料工序'),
      number('materialId', '物料ID'),
      input('materialCode', '物料编码'),
      input('materialName', '物料名称'),
      input('unit', '单位'),
      number('requiredQty', '需求数量'),
      number('allocatedQty', '分配数量'),
      number('pickedQty', '拣料数量'),
      number('consumedQty', '消耗数量'),
      number('lackQty', '缺料数量'),
      select('keyFlag', '是否关键物料', YES_NO),
      select('kitStatus', '齐套状态', KIT_STATUS),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'mt',
    moduleName: '物料管理',
    path: '/mes/mt/consumption',
    title: '物料消耗',
    columns: [
      col('consumeNo', '消耗单号'),
      col('materialId', '物料ID'),
      col('materialCode', '物料编码'),
      col('materialBatchNo', '物料批次号'),
      col('productionOrderId', '工单ID'),
      col('orderNo', '工单号'),
      col('serialNo', '产品序列号'),
      col('operationTaskId', '工序任务ID'),
      col('operationCode', '工序编码'),
      col('consumedQty', '消耗数量'),
      col('substituteFlag', '是否替代料'),
      col('operatorNo', '操作员'),
      col('wmsTransactionNo', 'WMS库存事务号'),
      col('syncStatus', '同步状态'),
      col('consumedAt', '消耗时间'),
      col('idempotencyKey', '幂等键')
    ],
    form: [
      input('consumeNo', '消耗单号'),
      number('materialId', '物料ID'),
      input('materialCode', '物料编码'),
      input('materialBatchNo', '物料批次号'),
      number('productionOrderId', '工单ID'),
      input('orderNo', '工单号'),
      input('serialNo', '产品序列号'),
      number('operationTaskId', '工序任务ID'),
      input('operationCode', '工序编码'),
      number('consumedQty', '消耗数量'),
      select('substituteFlag', '是否替代料', YES_NO),
      input('operatorNo', '操作员'),
      input('wmsTransactionNo', 'WMS库存事务号'),
      select('syncStatus', '同步状态', SYNC_STATUS),
      date('consumedAt', '消耗时间'),
      input('idempotencyKey', '幂等键')
    ]
  },
  {
    module: 'mt',
    moduleName: '物料管理',
    path: '/mes/mt/returnMaterial',
    title: '退料',
    columns: [
      col('returnNo', '退料单号'),
      col('productionOrderId', '工单ID'),
      col('orderNo', '工单号'),
      col('materialId', '物料ID'),
      col('materialCode', '物料编码'),
      col('materialBatchNo', '物料批次号'),
      col('originPickingNo', '原领料单'),
      col('returnQty', '退料数量'),
      col('reason', '退料原因'),
      col('warehouseConfirmStatus', '仓库确认状态'),
      col('status', '状态')
    ],
    form: [
      input('returnNo', '退料单号'),
      number('productionOrderId', '工单ID'),
      input('orderNo', '工单号'),
      number('materialId', '物料ID'),
      input('materialCode', '物料编码'),
      input('materialBatchNo', '物料批次号'),
      input('originPickingNo', '原领料单'),
      number('returnQty', '退料数量'),
      textarea('reason', '退料原因'),
      select('warehouseConfirmStatus', '仓库确认状态', WAREHOUSE_CONFIRM_STATUS),
      select('status', '状态', STATUS)
    ]
  },
  {
    module: 'mt',
    moduleName: '物料管理',
    path: '/mes/mt/supplementMaterial',
    title: '补料',
    columns: [
      col('supplementNo', '补料单号'),
      col('productionOrderId', '工单ID'),
      col('orderNo', '工单号'),
      col('materialId', '物料ID'),
      col('materialCode', '物料编码'),
      col('materialBatchNo', '物料批次号'),
      col('supplementQty', '补料数量'),
      col('reason', '补料原因'),
      col('status', '状态')
    ],
    form: [
      input('supplementNo', '补料单号'),
      number('productionOrderId', '工单ID'),
      input('orderNo', '工单号'),
      number('materialId', '物料ID'),
      input('materialCode', '物料编码'),
      input('materialBatchNo', '物料批次号'),
      number('supplementQty', '补料数量'),
      textarea('reason', '补料原因'),
      select('status', '状态', STATUS)
    ]
  },

  // ======================= 质量管理 qc =======================
  {
    module: 'qc',
    moduleName: '质量管理',
    path: '/mes/qc/inspection',
    title: '检验单',
    columns: [
      col('inspectionNo', '检验单号'),
      col('inspectionType', '检验类型'),
      col('productionOrderId', '工单ID'),
      col('orderNo', '工单号'),
      col('operationTaskId', '工序任务ID'),
      col('operationCode', '工序编码'),
      col('serialNo', '产品序列号'),
      col('materialId', '物料ID'),
      col('materialBatchNo', '物料批次号'),
      col('inspectionPlanId', '检验方案'),
      col('result', '检验结果'),
      col('inspectorNo', '检验员'),
      col('inspectedAt', '检验时间'),
      col('releaseFlag', '是否已放行')
    ],
    form: [
      input('inspectionNo', '检验单号'),
      select('inspectionType', '检验类型', INSPECTION_TYPE),
      number('productionOrderId', '工单ID'),
      input('orderNo', '工单号'),
      number('operationTaskId', '工序任务ID'),
      input('operationCode', '工序编码'),
      input('serialNo', '产品序列号'),
      number('materialId', '物料ID'),
      input('materialBatchNo', '物料批次号'),
      number('inspectionPlanId', '检验方案'),
      select('result', '检验结果', INSPECTION_RESULT),
      input('inspectorNo', '检验员'),
      date('inspectedAt', '检验时间'),
      select('releaseFlag', '是否已放行', YES_NO)
    ]
  },
  {
    module: 'qc',
    moduleName: '质量管理',
    path: '/mes/qc/inspectionValue',
    title: '检验值',
    columns: [
      col('inspectionId', '检验单ID'),
      col('itemCode', '项目编码'),
      col('itemName', '项目名称'),
      col('itemValue', '检验值'),
      col('lowerLimit', '下限'),
      col('upperLimit', '上限'),
      col('result', '判定结果')
    ],
    form: [
      number('inspectionId', '检验单ID'),
      input('itemCode', '项目编码'),
      input('itemName', '项目名称'),
      input('itemValue', '检验值'),
      number('lowerLimit', '下限'),
      number('upperLimit', '上限'),
      select('result', '判定结果', INSPECTION_RESULT)
    ]
  },
  {
    module: 'qc',
    moduleName: '质量管理',
    path: '/mes/qc/nonconformity',
    title: '不合格品',
    columns: [
      col('nonconformityNo', '不合格单号'),
      col('productionOrderId', '工单ID'),
      col('orderNo', '工单号'),
      col('operationTaskId', '工序任务ID'),
      col('operationCode', '工序编码'),
      col('serialNo', '产品序列号'),
      col('defectCode', '缺陷代码'),
      col('defectDesc', '缺陷描述'),
      col('discoverySource', '发现来源'),
      col('discoveryNo', '发现单单据'),
      col('qty', '不合格数量'),
      col('disposalType', '处置方式'),
      col('dispositionReason', '判定理由'),
      col('dispositionBy', '判定人'),
      col('dispositionTime', '判定时间'),
      col('authorizedBy', '授权人'),
      col('reinspectResult', '复检结果'),
      col('status', '状态')
    ],
    form: [
      input('nonconformityNo', '不合格单号'),
      number('productionOrderId', '工单ID'),
      input('orderNo', '工单号'),
      number('operationTaskId', '工序任务ID'),
      input('operationCode', '工序编码'),
      input('serialNo', '产品序列号'),
      input('defectCode', '缺陷代码'),
      textarea('defectDesc', '缺陷描述'),
      input('discoverySource', '发现来源'),
      input('discoveryNo', '发现单单据'),
      number('qty', '不合格数量'),
      select('disposalType', '处置方式', DISPOSITION_TYPE),
      textarea('dispositionReason', '判定理由'),
      input('dispositionBy', '判定人'),
      date('dispositionTime', '判定时间'),
      input('authorizedBy', '授权人'),
      select('reinspectResult', '复检结果', INSPECTION_RESULT),
      select('status', '状态', NONCONFORMITY_STATUS)
    ]
  },
  {
    module: 'qc',
    moduleName: '质量管理',
    path: '/mes/qc/rework',
    title: '返工记录',
    columns: [
      col('reworkNo', '返工单号'),
      col('nonconformityId', '不合格记录ID'),
      col('productionOrderId', '工单ID'),
      col('serialNo', '产品序列号'),
      col('operationCode', '返工工序'),
      col('reworkQty', '返工数量'),
      col('reworkResult', '复检结果'),
      col('executorNo', '执行人'),
      col('status', '状态')
    ],
    form: [
      input('reworkNo', '返工单号'),
      number('nonconformityId', '不合格记录ID'),
      number('productionOrderId', '工单ID'),
      input('serialNo', '产品序列号'),
      input('operationCode', '返工工序'),
      number('reworkQty', '返工数量'),
      select('reworkResult', '复检结果', INSPECTION_RESULT),
      input('executorNo', '执行人'),
      select('status', '状态', REWORK_STATUS)
    ]
  },
  {
    module: 'qc',
    moduleName: '质量管理',
    path: '/mes/qc/release',
    title: '放行单',
    columns: [
      col('releaseNo', '放行单号'),
      col('productionOrderId', '工单ID'),
      col('orderNo', '工单号'),
      col('serialNo', '产品序列号'),
      col('releaseResult', '放行结果'),
      col('releaseBy', '放行人'),
      col('releaseTime', '放行时间'),
      col('specialFlag', '是否特殊放行'),
      col('blockReason', '阻塞项')
    ],
    form: [
      input('releaseNo', '放行单号'),
      number('productionOrderId', '工单ID'),
      input('orderNo', '工单号'),
      input('serialNo', '产品序列号'),
      select('releaseResult', '放行结果', INSPECTION_RESULT),
      input('releaseBy', '放行人'),
      date('releaseTime', '放行时间'),
      select('specialFlag', '是否特殊放行', YES_NO),
      textarea('blockReason', '阻塞项')
    ]
  },

  // ======================= 设备管理 eq =======================
  {
    module: 'eq',
    moduleName: '设备管理',
    path: '/mes/eq/status',
    title: '设备状态',
    columns: [
      col('equipmentId', '设备ID'),
      col('equipmentCode', '设备编码'),
      col('status', '设备状态'),
      col('changedAt', '变更时间')
    ],
    form: [
      number('equipmentId', '设备ID'),
      input('equipmentCode', '设备编码'),
      select('status', '设备状态', EQUIPMENT_STATUS),
      date('changedAt', '变更时间')
    ]
  },
  {
    module: 'eq',
    moduleName: '设备管理',
    path: '/mes/eq/oee',
    title: '设备OEE',
    columns: [
      col('equipmentId', '设备ID'),
      col('equipmentCode', '设备编码'),
      col('statDate', '统计日期'),
      col('availability', '开动率'),
      col('performance', '性能效率'),
      col('quality', '良品率'),
      col('oee', 'OEE'),
      col('runMinutes', '运行时长(分)'),
      col('stopMinutes', '停机时长(分)')
    ],
    form: [
      number('equipmentId', '设备ID'),
      input('equipmentCode', '设备编码'),
      date('statDate', '统计日期'),
      number('availability', '开动率'),
      number('performance', '性能效率'),
      number('quality', '良品率'),
      number('oee', 'OEE'),
      number('runMinutes', '运行时长(分)'),
      number('stopMinutes', '停机时长(分)')
    ]
  },
  {
    module: 'eq',
    moduleName: '设备管理',
    path: '/mes/eq/maintenance',
    title: '设备维护',
    columns: [
      col('equipmentId', '设备ID'),
      col('equipmentCode', '设备编码'),
      col('maintenanceType', '维护类型'),
      col('stopReason', '停机原因分类'),
      col('startTime', '开始时间'),
      col('endTime', '结束时间'),
      col('stopMinutes', '停机时长(分)'),
      col('executorNo', '执行人'),
      col('status', '状态')
    ],
    form: [
      number('equipmentId', '设备ID'),
      input('equipmentCode', '设备编码'),
      select('maintenanceType', '维护类型', MAINTENANCE_TYPE),
      input('stopReason', '停机原因分类'),
      date('startTime', '开始时间'),
      date('endTime', '结束时间'),
      number('stopMinutes', '停机时长(分)'),
      input('executorNo', '执行人'),
      select('status', '状态', MAINTENANCE_STATUS)
    ]
  },
  {
    module: 'eq',
    moduleName: '设备管理',
    path: '/mes/eq/spotCheck',
    title: '设备点检',
    columns: [
      col('equipmentId', '设备ID'),
      col('equipmentCode', '设备编码'),
      col('planTime', '计划时间'),
      col('checkResult', '点检结果'),
      col('checkerNo', '点检人'),
      col('problemDesc', '问题描述'),
      col('repairRequired', '是否需维修')
    ],
    form: [
      number('equipmentId', '设备ID'),
      input('equipmentCode', '设备编码'),
      date('planTime', '计划时间'),
      select('checkResult', '点检结果', CHECK_RESULT),
      input('checkerNo', '点检人'),
      textarea('problemDesc', '问题描述'),
      select('repairRequired', '是否需维修', YES_NO)
    ]
  },
  {
    module: 'eq',
    moduleName: '设备管理',
    path: '/mes/eq/alarm',
    title: '设备报警',
    columns: [
      col('equipmentId', '设备ID'),
      col('equipmentCode', '设备编码'),
      col('alarmCode', '报警代码'),
      col('alarmLevel', '报警等级'),
      col('alarmDesc', '报警描述'),
      col('productionOrderId', '工单ID'),
      col('operationCode', '工序编码'),
      col('startTime', '开始时间'),
      col('endTime', '恢复时间'),
      col('duration', '持续时长(分)')
    ],
    form: [
      number('equipmentId', '设备ID'),
      input('equipmentCode', '设备编码'),
      input('alarmCode', '报警代码'),
      select('alarmLevel', '报警等级', ALARM_LEVEL),
      textarea('alarmDesc', '报警描述'),
      number('productionOrderId', '工单ID'),
      input('operationCode', '工序编码'),
      date('startTime', '开始时间'),
      date('endTime', '恢复时间'),
      number('duration', '持续时长(分)')
    ]
  },
  {
    module: 'eq',
    moduleName: '设备管理',
    path: '/mes/eq/command',
    title: '设备指令',
    columns: [
      col('commandNo', '命令编号'),
      col('equipmentId', '设备ID'),
      col('equipmentCode', '设备编码'),
      col('commandType', '命令类型'),
      col('productionOrderId', '工单ID'),
      col('serialNo', '序列号'),
      col('programCode', '程序/配方'),
      col('params', '参数'),
      col('sendBy', '下发人'),
      col('sendTime', '下发时间'),
      col('responseResult', '响应结果'),
      col('retryCount', '重试次数'),
      col('status', '状态')
    ],
    form: [
      input('commandNo', '命令编号'),
      number('equipmentId', '设备ID'),
      input('equipmentCode', '设备编码'),
      select('commandType', '命令类型', COMMAND_TYPE),
      number('productionOrderId', '工单ID'),
      input('serialNo', '序列号'),
      input('programCode', '程序/配方'),
      textarea('params', '参数'),
      input('sendBy', '下发人'),
      date('sendTime', '下发时间'),
      textarea('responseResult', '响应结果'),
      number('retryCount', '重试次数'),
      select('status', '状态', COMMAND_STATUS)
    ]
  },
  {
    module: 'eq',
    moduleName: '设备管理',
    path: '/mes/eq/bind',
    title: '设备绑定',
    columns: [
      col('equipmentId', '设备ID'),
      col('equipmentCode', '设备编码'),
      col('productionOrderId', '工单ID'),
      col('operationCode', '工序编码'),
      col('serialNo', '序列号'),
      col('shiftCode', '班次'),
      col('operatorNo', '操作员'),
      col('bindTime', '绑定时间'),
      col('unbindTime', '解绑时间')
    ],
    form: [
      number('equipmentId', '设备ID'),
      input('equipmentCode', '设备编码'),
      number('productionOrderId', '工单ID'),
      input('operationCode', '工序编码'),
      input('serialNo', '序列号'),
      input('shiftCode', '班次'),
      input('operatorNo', '操作员'),
      date('bindTime', '绑定时间'),
      date('unbindTime', '解绑时间')
    ]
  },

  // ======================= 追溯管理 tr =======================
  {
    module: 'tr',
    moduleName: '追溯管理',
    path: '/mes/tr/index',
    title: '追溯索引',
    columns: [
      col('serialNo', '产品序列号'),
      col('productionOrderId', '工单ID'),
      col('orderNo', '工单号'),
      col('materialBatchNo', '物料批次号'),
      col('equipmentCode', '设备编码'),
      col('operatorNo', '操作员'),
      col('operationCode', '工序编码'),
      col('routeVersion', '工艺版本'),
      col('refType', '业务类型'),
      col('refId', '业务ID'),
      col('bizTime', '业务时间')
    ],
    form: [
      input('serialNo', '产品序列号'),
      number('productionOrderId', '工单ID'),
      input('orderNo', '工单号'),
      input('materialBatchNo', '物料批次号'),
      input('equipmentCode', '设备编码'),
      input('operatorNo', '操作员'),
      input('operationCode', '工序编码'),
      input('routeVersion', '工艺版本'),
      input('refType', '业务类型'),
      number('refId', '业务ID'),
      date('bizTime', '业务时间')
    ]
  },

  // ======================= 集成管理 integration =======================
  {
    module: 'integration',
    moduleName: '集成管理',
    path: '/mes/integration/message',
    title: '集成消息',
    columns: [
      col('messageId', '消息编号'),
      col('messageType', '消息类型'),
      col('messageVersion', '消息版本'),
      col('sourceSystem', '来源系统'),
      col('targetSystem', '目标系统'),
      col('plantCode', '工厂编码'),
      col('traceId', '链路追踪编号'),
      col('idempotencyKey', '幂等键'),
      col('direction', '方向'),
      col('payload', '消息载荷'),
      col('status', '消息状态'),
      col('retryCount', '重试次数'),
      col('nextRetryTime', '下次重试时间'),
      col('errorMsg', '错误信息'),
      col('occurredAt', '发生时间'),
      col('processedAt', '处理时间')
    ],
    form: [
      input('messageId', '消息编号'),
      input('messageType', '消息类型'),
      input('messageVersion', '消息版本'),
      input('sourceSystem', '来源系统'),
      input('targetSystem', '目标系统'),
      input('plantCode', '工厂编码'),
      input('traceId', '链路追踪编号'),
      input('idempotencyKey', '幂等键'),
      select('direction', '方向', DIRECTION),
      textarea('payload', '消息载荷'),
      select('status', '消息状态', MESSAGE_STATUS),
      number('retryCount', '重试次数'),
      date('nextRetryTime', '下次重试时间'),
      textarea('errorMsg', '错误信息'),
      date('occurredAt', '发生时间'),
      date('processedAt', '处理时间')
    ]
  },
  {
    module: 'integration',
    moduleName: '集成管理',
    path: '/mes/integration/event',
    title: '领域事件',
    columns: [
      col('eventId', '事件编号'),
      col('eventType', '事件类型'),
      col('aggregateType', '聚合类型'),
      col('aggregateId', '聚合ID'),
      col('payload', '事件载荷'),
      col('traceId', '链路追踪编号'),
      col('status', '状态'),
      col('retryCount', '重试次数'),
      col('nextRetryTime', '下次重试时间'),
      col('errorMsg', '错误信息'),
      col('occurredAt', '发生时间'),
      col('processedAt', '处理时间')
    ],
    form: [
      input('eventId', '事件编号'),
      input('eventType', '事件类型'),
      input('aggregateType', '聚合类型'),
      number('aggregateId', '聚合ID'),
      textarea('payload', '事件载荷'),
      input('traceId', '链路追踪编号'),
      select('status', '状态', MESSAGE_STATUS),
      number('retryCount', '重试次数'),
      date('nextRetryTime', '下次重试时间'),
      textarea('errorMsg', '错误信息'),
      date('occurredAt', '发生时间'),
      date('processedAt', '处理时间')
    ]
  },
  {
    module: 'integration',
    moduleName: '集成管理',
    path: '/mes/integration/deadLetter',
    title: '死信',
    columns: [
      col('messageId', '消息编号'),
      col('bizType', '业务类型'),
      col('payload', '消息载荷'),
      col('errorMsg', '错误信息'),
      col('handler', '责任人'),
      col('handleStatus', '处理状态'),
      col('failedAt', '失败时间')
    ],
    form: [
      input('messageId', '消息编号'),
      input('bizType', '业务类型'),
      textarea('payload', '消息载荷'),
      textarea('errorMsg', '错误信息'),
      input('handler', '责任人'),
      select('handleStatus', '处理状态', HANDLE_STATUS),
      date('failedAt', '失败时间')
    ]
  },
  {
    module: 'integration',
    moduleName: '集成管理',
    path: '/mes/integration/idempotent',
    title: '幂等记录',
    columns: [
      col('idempotencyKey', '幂等键'),
      col('bizType', '业务类型'),
      col('bizId', '业务ID'),
      col('resultCode', '结果码'),
      col('resultBody', '结果内容')
    ],
    form: [
      input('idempotencyKey', '幂等键'),
      input('bizType', '业务类型'),
      number('bizId', '业务ID'),
      input('resultCode', '结果码'),
      textarea('resultBody', '结果内容')
    ]
  },
  {
    module: 'integration',
    moduleName: '集成管理',
    path: '/mes/integration/reconcileTask',
    title: '对账任务',
    columns: [
      col('taskNo', '任务编号'),
      col('targetSystem', '对账对象'),
      col('bizDate', '对账日期'),
      col('status', '状态'),
      col('diffCount', '差异数量'),
      col('startTime', '开始时间'),
      col('endTime', '结束时间')
    ],
    form: [
      input('taskNo', '任务编号'),
      input('targetSystem', '对账对象'),
      date('bizDate', '对账日期'),
      select('status', '状态', RECONCILE_STATUS),
      number('diffCount', '差异数量'),
      date('startTime', '开始时间'),
      date('endTime', '结束时间')
    ]
  },
  {
    module: 'integration',
    moduleName: '集成管理',
    path: '/mes/integration/reconcileResult',
    title: '对账结果',
    columns: [
      col('taskId', '对账任务ID'),
      col('targetSystem', '对账对象'),
      col('bizType', '业务类型'),
      col('bizKey', '业务键'),
      col('diffType', '差异类型'),
      col('mesValue', 'MES值'),
      col('extValue', '外部值'),
      col('handleStatus', '处理状态')
    ],
    form: [
      number('taskId', '对账任务ID'),
      input('targetSystem', '对账对象'),
      input('bizType', '业务类型'),
      input('bizKey', '业务键'),
      input('diffType', '差异类型'),
      input('mesValue', 'MES值'),
      input('extValue', '外部值'),
      select('handleStatus', '处理状态', HANDLE_STATUS)
    ]
  }
]

// 自动推导权限标识前缀：/mes/md/material -> mes:md:material
const resources = RESOURCES.map((item) => ({
  ...item,
  perm: item.perm || item.path.replace(/^\//, '').split('/').join(':')
}))

// 模块展示顺序
export const MODULE_ORDER = ['md', 'om', 'ps', 'wo', 'ex', 'mt', 'qc', 'eq', 'tr', 'integration']

// 按模块分组（用于侧边菜单）
export function getGroupedResources() {
  const groups = []
  MODULE_ORDER.forEach((moduleCode) => {
    const list = resources.filter((r) => r.module === moduleCode)
    if (list.length > 0) {
      groups.push({
        module: moduleCode,
        moduleName: list[0].moduleName,
        children: list
      })
    }
  })
  return groups
}

// 根据路由路径获取资源配置
export function getResourceByPath(path) {
  return resources.find((r) => r.path === path)
}

// 根据权限标识获取资源配置
export function getResourceByPerm(perm) {
  return resources.find((r) => r.perm === perm)
}

export default resources
