/* ============================================================
   MES 制造与供应链协同平台 · 通用应用外壳 + MESUI 组件库
   ------------------------------------------------------------
   · 全站唯一的导航 / 顶栏 / 图标源，页面只负责主内容区
   · 组件库为纯函数式：MESUI.xxx(mount, data) → 生成 HTML
   · 无第三方依赖，可离线打开
   ============================================================ */

(function () {
  'use strict';

  /* ---------------- 图标（Lucide 风格线性图标） ---------------- */
  var ICONS = {
    dashboard: '<rect x="3" y="3" width="7" height="9" rx="1"/><rect x="14" y="3" width="7" height="5" rx="1"/><rect x="14" y="12" width="7" height="9" rx="1"/><rect x="3" y="16" width="7" height="5" rx="1"/>',
    screen: '<rect x="2" y="4" width="20" height="13" rx="2"/><path d="M8 21h8M12 17v4"/>',
    calendar: '<rect x="3" y="4" width="18" height="17" rx="2"/><path d="M8 2v4M16 2v4M3 10h18"/>',
    play: '<circle cx="12" cy="12" r="9"/><path d="M10 8.5l6 3.5-6 3.5z"/>',
    box: '<path d="M21 8l-9-5-9 5 9 5 9-5z"/><path d="M3 8v8l9 5 9-5V8"/><path d="M12 13v8"/>',
    shield: '<path d="M12 3l8 3v6c0 4.5-3.4 7.9-8 9-4.6-1.1-8-4.5-8-9V6l8-3z"/><path d="M9 12l2 2 4-4"/>',
    trace: '<circle cx="11" cy="11" r="7"/><path d="M21 21l-4.3-4.3"/><path d="M8 11h6M11 8v6"/>',
    cpu: '<rect x="5" y="5" width="14" height="14" rx="2"/><rect x="9" y="9" width="6" height="6" rx="1"/><path d="M9 2v3M15 2v3M9 19v3M15 19v3M2 9h3M2 15h3M19 9h3M19 15h3"/>',
    chart: '<path d="M3 21h18"/><path d="M6 17V9M11 17V5M16 17v-6M21 17v-9"/>',
    database: '<ellipse cx="12" cy="5.5" rx="8" ry="3"/><path d="M4 5.5v13c0 1.7 3.6 3 8 3s8-1.3 8-3v-13"/><path d="M4 12c0 1.7 3.6 3 8 3s8-1.3 8-3"/>',
    plug: '<path d="M9 2v6M15 2v6"/><path d="M6 8h12v3a6 6 0 0 1-12 0V8z"/><path d="M12 17v5"/>',
    clock: '<circle cx="12" cy="12" r="9"/><path d="M12 7v5l3 2"/>',
    bell: '<path d="M18 9a6 6 0 1 0-12 0c0 5-2 6-2 6h16s-2-1-2-6"/><path d="M10.5 20a2 2 0 0 0 3 0"/>',
    user: '<circle cx="12" cy="8" r="4"/><path d="M4 21c0-3.5 3.6-6 8-6s8 2.5 8 6"/>',
    chevronDown: '<path d="m6 9 6 6 6-6"/>',
    chevronRight: '<path d="m9 6 6 6-6 6"/>',
    arrowRight: '<path d="M4 12h16"/><path d="m14 6 6 6-6 6"/>',
    download: '<path d="M12 3v12"/><path d="m7 10 5 5 5-5"/><path d="M4 21h16"/>',
    refresh: '<path d="M20 11A8 8 0 1 0 18 6"/><path d="M20 4v7h-7"/>',
    filter: '<path d="M3 5h18l-7 8v6l-4 2v-8z"/>',
    plus: '<path d="M12 5v14M5 12h14"/>',
    x: '<path d="M6 6l12 12M18 6 6 18"/>',
    check: '<path d="m4 12 5 5L20 6"/>',
    alert: '<path d="M12 3 2.5 20h19z"/><path d="M12 9v5M12 17.5v.5"/>',
    printer: '<path d="M7 8V3h10v5"/><rect x="3" y="8" width="18" height="8" rx="2"/><path d="M7 14h10v7H7z"/>',
    settings: '<circle cx="12" cy="12" r="3"/><path d="M19.4 15a1.7 1.7 0 0 0 .3 1.9l.1.1a2 2 0 1 1-2.8 2.8l-.1-.1a1.7 1.7 0 0 0-1.9-.3 1.7 1.7 0 0 0-1 1.5V21a2 2 0 1 1-4 0v-.1A1.7 1.7 0 0 0 8.9 19a1.7 1.7 0 0 0-1.9.3l-.1.1a2 2 0 1 1-2.8-2.8l.1-.1a1.7 1.7 0 0 0 .3-1.9 1.7 1.7 0 0 0-1.5-1H3a2 2 0 1 1 0-4h.1A1.7 1.7 0 0 0 5 8.9a1.7 1.7 0 0 0-.3-1.9l-.1-.1a2 2 0 1 1 2.8-2.8l.1.1a1.7 1.7 0 0 0 1.9.3H9a1.7 1.7 0 0 0 1-1.5V3a2 2 0 1 1 4 0v.1a1.7 1.7 0 0 0 1 1.5 1.7 1.7 0 0 0 1.9-.3l.1-.1a2 2 0 1 1 2.8 2.8l-.1.1a1.7 1.7 0 0 0-.3 1.9V9a1.7 1.7 0 0 0 1.5 1H21a2 2 0 1 1 0 4h-.1a1.7 1.7 0 0 0-1.5 1z"/>',
    logout: '<path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/><path d="m16 17 5-5-5-5"/><path d="M21 12H9"/>',
    menu: '<path d="M3 6h18M3 12h18M3 18h18"/>',
    search: '<circle cx="11" cy="11" r="7"/><path d="M21 21l-4.3-4.3"/>',
    activity: '<path d="M3 12h4l3 8 4-16 3 8h4"/>',
    layers: '<path d="m12 3 9 5-9 5-9-5 9-5z"/><path d="m3 13 9 5 9-5"/><path d="m3 17 9 5 9-5"/>',
    gauge: '<path d="M4.5 19a9 9 0 1 1 15 0"/><path d="M12 12l4-4"/><circle cx="12" cy="12" r="1.5"/>',
    trend: '<path d="m3 17 6-6 4 4 8-8"/><path d="M15 7h6v6"/>',
    package: '<path d="M16 3H8L4 8v13h16V8l-4-5z"/><path d="M4 8h16M9 12h6"/>',
    gitBranch: '<circle cx="6" cy="6" r="2.5"/><circle cx="18" cy="18" r="2.5"/><path d="M6 8.5V15a3 3 0 0 0 3 3h6.5"/><path d="M15.5 6H12a3 3 0 0 0-3 3v1"/>',
    fileText: '<path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/><path d="M14 2v6h6M8 13h8M8 17h6"/>',
    clipboard: '<rect x="5" y="4" width="14" height="17" rx="2"/><path d="M9 4V3h6v1"/><path d="m9 13 2 2 4-4"/>',
    tool: '<path d="M14.7 6.3a4.5 4.5 0 0 0 6 6L15 18l-3 3-3-3 5.7-5.7z"/><path d="m6 6 4 4"/>',
    link: '<path d="M10 13a5 5 0 0 0 7 0l2-2a5 5 0 0 0-7-7l-1 1"/><path d="M14 11a5 5 0 0 0-7 0l-2 2a5 5 0 0 0 7 7l1-1"/>',
    sync: '<path d="M21 12a9 9 0 0 1-15.5 6.2"/><path d="M3 12a9 9 0 0 1 15.5-6.2"/><path d="M3 17v-4h4M21 7v4h-4"/>',
    edit: '<path d="M12 20h9"/><path d="M16.5 3.5a2.1 2.1 0 0 1 3 3L7 19l-4 1 1-4z"/>',
    eye: '<path d="M2 12s3.5-7 10-7 10 7 10 7-3.5 7-10 7-10-7-10-7z"/><circle cx="12" cy="12" r="3"/>',
    arrowUp: '<path d="M12 19V5"/><path d="m6 11 6-6 6 6"/>',
    arrowDown: '<path d="M12 5v14"/><path d="m18 13-6 6-6-6"/>',
    target: '<circle cx="12" cy="12" r="9"/><circle cx="12" cy="12" r="5"/><circle cx="12" cy="12" r="1"/>',
    truck: '<path d="M3 16V6h11v10"/><path d="M14 9h4l3 3v4h-7z"/><circle cx="7" cy="18" r="2"/><circle cx="17" cy="18" r="2"/>',
    warehouse: '<path d="M3 21V9l9-5 9 5v12"/><path d="M7 21v-7h10v7"/><path d="M7 17h10"/>',
    users: '<circle cx="9" cy="8" r="3.5"/><path d="M2.5 21c0-3.3 2.9-6 6.5-6s6.5 2.7 6.5 6"/><path d="M16 5.2a3.5 3.5 0 0 1 0 6.6M18 15.4c2.1.8 3.5 2.5 3.5 4.6"/>',
    wrench: '<path d="M21 4a5 5 0 0 1-6.2 6.2L6 19a2.1 2.1 0 1 1-3-3l8.8-8.8A5 5 0 0 1 18 1l-3 3 2 2z"/>',
    flag: '<path d="M5 21V4h13l-2.5 4L18 12H5"/>',
    copy: '<rect x="9" y="9" width="12" height="12" rx="2"/><path d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"/>'
  };

  function icon(name, size) {
    var inner = ICONS[name] || ICONS.chart;
    var s = size || 16;
    return '<svg width="' + s + '" height="' + s + '" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true">' + inner + '</svg>';
  }

  /* ---------------- 导航（唯一来源） ---------------- */
  var NAV = [
    {
      group: '运营中心',
      items: [
        { page: 'index', href: 'index.html', title: '生产总览', icon: 'dashboard' },
        { page: 'scm', href: 'scm.html', title: '供应链总览', icon: 'link' },
        { page: 'board', href: 'board.html', title: '车间大屏', icon: 'screen' }
      ]
    },
    {
      group: '计划与执行',
      items: [
        { page: 'plan', href: 'plan.html', title: '计划管理', icon: 'calendar' },
        { page: 'manufacturing', href: 'manufacturing.html', title: '生产制造', icon: 'gitBranch' },
        { page: 'production', href: 'production.html', title: '生产执行', icon: 'play', badge: '3' },
        { page: 'material', href: 'material.html', title: '线边物料', icon: 'package', badge: '2' }
      ]
    },
    {
      group: '供应链协同',
      items: [
        { page: 'purchase', href: 'purchase.html', title: '采购管理', icon: 'clipboard', badge: '6' },
        { page: 'sales', href: 'sales.html', title: '销售管理', icon: 'truck' },
        { page: 'inventory', href: 'inventory.html', title: '库存管理', icon: 'warehouse' },
        { page: 'collab', href: 'collab.html', title: '协同管理', icon: 'users', badge: '5' },
        { page: 'costing', href: 'costing.html', title: '存货核算', icon: 'fileText' }
      ]
    },
    {
      group: '质量与追溯',
      items: [
        { page: 'quality', href: 'quality.html', title: '质量管理', icon: 'shield', badge: '4' },
        { page: 'trace', href: 'trace.html', title: '质量追溯', icon: 'trace' }
      ]
    },
    {
      group: '资源与分析',
      items: [
        { page: 'equipment', href: 'equipment.html', title: '设备管理', icon: 'cpu' },
        { page: 'analysis', href: 'analysis.html', title: '统计分析', icon: 'chart' }
      ]
    },
    {
      group: '平台配置',
      items: [
        { page: 'masterdata', href: 'masterdata.html', title: '基础数据', icon: 'database' },
        { page: 'system', href: 'system.html', title: '系统集成', icon: 'plug' }
      ]
    }
  ];

  var PAGE_META = {
    index: { title: '生产总览', group: '运营中心' },
    scm: { title: '供应链总览', group: '运营中心' },
    board: { title: '车间大屏', group: '运营中心' },
    plan: { title: '计划管理', group: '计划与执行' },
    manufacturing: { title: '生产制造', group: '计划与执行' },
    production: { title: '生产执行', group: '计划与执行' },
    material: { title: '线边物料', group: '计划与执行' },
    purchase: { title: '采购管理', group: '供应链协同' },
    sales: { title: '销售管理', group: '供应链协同' },
    inventory: { title: '库存管理', group: '供应链协同' },
    collab: { title: '协同管理', group: '供应链协同' },
    costing: { title: '存货核算', group: '供应链协同' },
    quality: { title: '质量管理', group: '质量与追溯' },
    trace: { title: '质量追溯', group: '质量与追溯' },
    equipment: { title: '设备管理', group: '资源与分析' },
    analysis: { title: '统计分析', group: '资源与分析' },
    masterdata: { title: '基础数据', group: '平台配置' },
    system: { title: '系统集成', group: '平台配置' }
  };

  /* ---------------- 工具 ---------------- */
  function $(sel, root) { return (root || document).querySelector(sel); }
  function $$(sel, root) { return Array.prototype.slice.call((root || document).querySelectorAll(sel)); }
  function el(mount) { return typeof mount === 'string' ? $(mount) : mount; }
  function esc(v) {
    return String(v == null ? '' : v).replace(/[&<>"']/g, function (c) {
      return { '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;' }[c];
    });
  }
  function toneOf(status) {
    var s = String(status || '');
    if (/已完成|正常|合格|通过|运行|在线|已关闭|达成|已发布|已入库|空闲|已检验|已同步|成功|已处理|已放行|已完工|已完成检/.test(s)) return 'ok';
    if (/异常|报警|失败|不合格|停机|缺料|超期|驳回|故障|离线|待处理|未通过|停用|报废|返工|未达标/.test(s)) return 'danger';
    if (/进行中|生产中|待检验|待确认|预警|待料|待开始|待发布|待收货|部分|加工中|调试/.test(s)) return 'warn';
    if (/待|新建|草稿|计划|排队|已排程|已锁定/.test(s)) return 'info';
    return 'mute';
  }
  function tag(text, tone) {
    if (text == null || text === '') return '<span class="muted">—</span>';
    return '<span class="tag ' + (tone || toneOf(text)) + '">' + esc(text) + '</span>';
  }
  function num(v) {
    if (typeof v === 'number') return v.toLocaleString('zh-CN');
    return esc(v);
  }
  function pad(n) { return n < 10 ? '0' + n : '' + n; }
  function nowStr() {
    var d = new Date();
    return pad(d.getHours()) + ':' + pad(d.getMinutes()) + ':' + pad(d.getSeconds());
  }
  function progress(pct, tone, size) {
    var p = Math.max(0, Math.min(100, Math.round(pct || 0)));
    return '<div class="progress ' + (size === 'lg' ? 'lg' : size === 'thin' ? 'thin' : '') + '"><div class="progress-bar ' + (tone || '') + '" style="width:' + p + '%"></div></div>';
  }
  function pctTone(p) { return p >= 95 ? 'ok' : p >= 80 ? 'warn' : 'danger'; }

  function invalid(input, msg) {
    if (!input) return;
    input.classList.add('invalid');
    var field = input.closest('.field');
    if (field) {
      var err = field.querySelector('.field-error');
      if (!err) { err = document.createElement('div'); err.className = 'field-error'; field.appendChild(err); }
      err.textContent = msg || '请填写有效的值';
    }
    try { input.focus(); } catch (e) {}
    var clear = function () {
      input.classList.remove('invalid');
      if (field) { var e2 = field.querySelector('.field-error'); if (e2) e2.remove(); }
      input.removeEventListener('input', clear);
      input.removeEventListener('change', clear);
    };
    input.addEventListener('input', clear);
    input.addEventListener('change', clear);
  }

  /* ============================================================
     跨页联动层
     - 演示数据持久化：页面间跳转后保留新增/修改的数据
     - URL 深链接：?tab=订单页签 &q=搜索词 &mount=表格容器 &serial=序列号
     - 供页面脚本调用：MES.save() / MES.go() / MES.query() / MES.reset()
     ============================================================ */
  var STORE_KEY = 'mes.sc.v25.demo';
  var STORE_VERSION = 2;
  var WIN_NAME_PREFIX = 'MESDEMO:';
  var suppressSave = false;

  function restoreDemo() {
    try {
      var raw = null;
      try { raw = localStorage.getItem(STORE_KEY); } catch (e) {}
      if (!raw && window.name && window.name.indexOf(WIN_NAME_PREFIX) === 0) {
        raw = window.name.slice(WIN_NAME_PREFIX.length);
      }
      if (!raw) return false;
      var snap = JSON.parse(raw);
      if (!snap || snap.version !== STORE_VERSION || !snap.db) return false;
      Object.keys(DB).forEach(function (k) { delete DB[k]; });
      Object.keys(snap.db).forEach(function (k) { DB[k] = snap.db[k]; });
      if (DB.scm && DB.scm.u8c) DB.u8c = DB.scm.u8c;
      DB.audit = DB.audit || [];
      window.DB = DB;
      return true;
    } catch (e) { return false; }
  }

  function saveDemo() {
    if (suppressSave) return false;
    var payload = JSON.stringify({ version: STORE_VERSION, savedAt: Date.now(), db: DB });
    try { localStorage.setItem(STORE_KEY, payload); } catch (e) {}
    try { window.name = WIN_NAME_PREFIX + payload; } catch (e) {}
    return true;
  }

  function resetDemo() {
    suppressSave = true;
    try { localStorage.removeItem(STORE_KEY); } catch (e) {}
    try { window.name = ''; } catch (e) {}
    location.reload();
  }

  function query(key) {
    try { return new URLSearchParams(location.search).get(key); } catch (e) { return null; }
  }

  function clearQuery() {
    try { history.replaceState(null, '', location.pathname); } catch (e) {}
  }

  function buildUrl(page, params) {
    if (!params) return page;
    var usp = new URLSearchParams();
    Object.keys(params).forEach(function (k) {
      if (params[k] !== undefined && params[k] !== null && params[k] !== '') usp.set(k, params[k]);
    });
    var qs = usp.toString();
    if (!qs) return page;
    return page + (page.indexOf('?') > -1 ? '&' : '?') + qs;
  }

  function go(page, params) {
    var url = buildUrl(page, params);
    startProgress();
    setTimeout(function () { location.href = url; }, 120);
  }

  var progressToken = 0;

  function startProgress() {
    var old = $('#pageProgress');
    if (old) old.remove();
    var bar = document.createElement('div');
    var token = ++progressToken;
    bar.id = 'pageProgress';
    bar.className = 'page-progress';
    document.body.appendChild(bar);
    bar.style.width = '8%';
    setTimeout(function () { if (token === progressToken && bar.parentNode) bar.style.width = '72%'; }, 40);
  }

  function finishProgress() {
    var bar = $('#pageProgress');
    if (!bar) return;
    var token = progressToken;
    bar.style.width = '100%';
    setTimeout(function () {
      if (token !== progressToken) return;
      bar.classList.add('done');
      setTimeout(function () {
        if (token === progressToken && bar.parentNode) bar.remove();
      }, 320);
    }, 180);
  }

  function activateTab(key) {
    if (!key) return false;
    var btn = document.querySelector('[data-tab="' + key + '"]');
    if (!btn) return false;
    btn.click();
    return true;
  }

  function applySearch(value, mount) {
    if (!value) return false;
    var root = mount ? (typeof mount === 'string' ? $(mount) : mount) : null;
    var input = root ? root.querySelector('[data-tb-search]') : null;
    if (!input) {
      if (mount) return false;
      input = document.querySelector('.tab-panel:not(.hidden) [data-tb-search]') ||
        document.querySelector('[data-tb-search]');
    }
    if (!input) return false;
    input.value = value;
    input.dispatchEvent(new Event('input', { bubbles: true }));
    return true;
  }

  function applyQuery() {
    var tab = query('tab');
    if (tab) activateTab(tab);
    var q = query('q');
    if (q) setTimeout(function () { applySearch(q, query('mount')); }, 80);
  }

  /* ---------------- 外壳 ---------------- */
  function currentPage() {
    var p = document.body.getAttribute('data-page');
    if (p) return p;
    var file = (location.pathname.split('/').pop() || 'index.html').replace('.html', '');
    return file || 'index';
  }

  function buildNav() {
    var mount = $('#appNav');
    if (!mount) return;
    var page = currentPage();
    var html = '<div class="nav-brand">' +
      '<span class="brand-mark">MES</span>' +
      '<span class="brand-text"><b>制造与供应链协同</b><i>ADX · GBE · v2.5</i></span>' +
    '</div><div class="nav-scroll">';
    NAV.forEach(function (g) {
      html += '<div class="nav-group"><div class="nav-group-label">' + esc(g.group) + '</div>';
      g.items.forEach(function (it) {
        html += '<a class="nav-item' + (it.page === page ? ' active' : '') + '" href="' + it.href + '" title="' + esc(it.title) + '">' +
          icon(it.icon) + '<span>' + esc(it.title) + '</span>' +
          (it.badge ? '<span class="nav-badge">' + esc(it.badge) + '</span>' : '') +
        '</a>';
      });
      html += '</div>';
    });
    html += '</div><div class="nav-foot"><span class="led ok pulse"></span><span>演示环境 · 数据跨页保留</span></div>';
    mount.innerHTML = html;
  }

  function buildTopbar() {
    var mount = $('#appTopbar');
    if (!mount) return;
    var page = currentPage();
    var meta = PAGE_META[page] || { title: '制造与供应链协同平台', group: '运营中心' };
    var plant = (window.DB && DB.plant) || { name: '爱达兴 · 高铂尔制造基地', workDate: '2026-10-25', currentShift: '白班 · 08:00-20:00' };
    var user = (window.DB && DB.user) || { name: '张明', dept: '制造运营部' };

    var notifs = (window.DB && DB.notifications) || [];
    var unread = notifs.filter(function (n) { return !n.read; }).length;

    mount.innerHTML =
      '<button class="icon-btn collapse-btn" id="navCollapse" title="收起 / 展开导航">' + icon('menu') + '</button>' +
      '<button class="icon-btn menu-btn" id="navToggle" aria-label="打开导航">' + icon('menu') + '</button>' +
      '<div class="crumb">' + icon('layers') + '<span>' + esc(meta.group) + '</span><span class="slash">/</span><b>' + esc(meta.title) + '</b></div>' +
      '<div class="topbar-spacer"></div>' +
      '<div class="relative">' +
        '<button class="icon-btn" id="quickBtn" title="快速新建">' + icon('plus') + '</button>' +
        '<div class="popover" id="quickPopover" style="min-width:252px">' +
          '<div class="popover-head">' + icon('plus') + '快速新建</div>' +
          '<a class="popover-item" href="purchase.html?new=1"><div class="pi-title">新建采购订单</div><div class="pi-meta">采购管理 · 供应商 / 物料 / 交期</div></a>' +
          '<a class="popover-item" href="sales.html?new=1"><div class="pi-title">新建销售订单</div><div class="pi-meta">销售管理 · 客户 / 产品 / 数量</div></a>' +
          '<a class="popover-item" href="production.html?new=1"><div class="pi-title">生产报工</div><div class="pi-meta">生产执行 · 工单 / 工序 / 报工</div></a>' +
          '<a class="popover-item" href="collab.html?new=exception&amp;tab=exception"><div class="pi-title">交付异常上报</div><div class="pi-meta">协同管理 · 逾期 / 质量 / 单据</div></a>' +
          '<a class="popover-item" href="inventory.html?new=in"><div class="pi-title">其他入库</div><div class="pi-meta">库存管理 · 仓库 / 物料 / 数量</div></a>' +
          '<a class="popover-item" href="costing.html?new=adjust"><div class="pi-title">新增调整单</div><div class="pi-meta">存货核算 · 出入库金额调整</div></a>' +
        '</div>' +
      '</div>' +
      '<button class="search-trigger" id="globalSearch">' + icon('search') + '<span>搜索页面 / 单号 / 物料…</span><kbd>Ctrl K</kbd></button>' +
      '<span class="top-chip hide-md">' + icon('calendar') + esc(plant.workDate) + '</span>' +
      '<span class="top-chip hide-md">' + icon('clock') + esc(plant.currentShift.split('·')[0]) + '</span>' +
      '<span class="clock" id="appClock">' + nowStr() + '</span>' +
      '<div class="relative">' +
        '<button class="icon-btn" id="bellBtn" aria-label="通知">' + icon('bell') +
          '<span class="notif-count" id="notifCount"' + (unread ? '' : ' style="display:none"') + '>' + unread + '</span>' +
        '</button>' +
        '<div class="popover" id="bellPopover">' +
          '<div class="popover-head">' + icon('bell') + '通知中心<span class="spacer"></span><button type="button" class="notif-head-btn" data-notif-all>全部已读</button></div>' +
          notifs.map(function (n, i) {
            var inner = '<div class="pi-title">' + esc(n.title) + '</div><div class="pi-meta">' + esc(n.time) + ' · ' + esc(n.from) + '</div>';
            var cls = 'popover-item' + (n.read ? '' : ' unread');
            return n.href
              ? '<a class="' + cls + '" data-notif="' + i + '" href="' + esc(n.href) + '">' + inner + '</a>'
              : '<div class="' + cls + '" data-notif="' + i + '">' + inner + '</div>';
          }).join('') +
        '</div>' +
      '</div>' +
      '<div class="relative">' +
        '<div class="user-trigger" id="userBtn">' +
          '<span class="avatar">' + esc(user.name.slice(-2)) + '</span>' +
          '<span><span class="u-name">' + esc(user.name) + '</span><br><span class="u-dept">' + esc(user.dept) + '</span></span>' +
          icon('chevronDown') +
        '</div>' +
        '<div class="popover" id="userPopover">' +
          '<div class="popover-head">' + icon('user') + esc(user.name) + '<span class="spacer"></span><span class="tag outline">' + esc(user.role || '管理员') + '</span></div>' +
          '<div class="popover-item" data-user-go="masterdata"><div class="pi-title">个人与权限</div><div class="pi-meta">账号 / 角色 / 通知设置</div></div>' +
          '<div class="popover-item" data-user-go="audit"><div class="pi-title">操作日志</div><div class="pi-meta">最近操作与数据变更记录</div></div>' +
          '<div class="popover-item" data-user-go="help"><div class="pi-title">键盘快捷键</div><div class="pi-meta">Ctrl K / ? / Esc</div></div>' +
          '<div class="popover-item" data-user-go="system"><div class="pi-title">系统设置</div><div class="pi-meta">参数 / 接口 / 日志</div></div>' +
          '<div class="popover-item" data-user-go="reset"><div class="pi-title" style="color:var(--warning)">重置演示数据</div><div class="pi-meta">清除本机改动，恢复初始演示数据</div></div>' +
          '<div class="popover-item" data-user-go="logout"><div class="pi-title" style="color:var(--danger)">退出登录</div></div>' +
        '</div>' +
      '</div>';

    var clock = $('#appClock');
    setInterval(function () { if (clock) clock.textContent = nowStr(); }, 1000);

    /* 侧边栏收起状态 */
    var collapsed = false;
    try { collapsed = localStorage.getItem('mes.nav.collapsed') === '1'; } catch (e) {}
    if (collapsed) document.body.classList.add('nav-collapsed');
    var collapseBtn = $('#navCollapse');
    if (collapseBtn) collapseBtn.addEventListener('click', function () {
      document.body.classList.toggle('nav-collapsed');
      try {
        localStorage.setItem('mes.nav.collapsed', document.body.classList.contains('nav-collapsed') ? '1' : '0');
      } catch (e) {}
    });

    var toggle = $('#navToggle');
    if (toggle) toggle.addEventListener('click', function (e) {
      e.stopPropagation();
      document.body.classList.toggle('nav-open');
    });
    document.addEventListener('click', function (e) {
      if (document.body.classList.contains('nav-open') && !e.target.closest('#appNav') && !e.target.closest('#navToggle')) {
        document.body.classList.remove('nav-open');
      }
    });

    var quickBtn = $('#quickBtn'), quickPop = $('#quickPopover');
    if (quickBtn && quickPop) quickBtn.addEventListener('click', function (e) {
      e.stopPropagation();
      closePopovers(quickPop);
      quickPop.classList.toggle('open');
    });

    var bellBtn = $('#bellBtn'), bellPop = $('#bellPopover');
    if (bellBtn && bellPop) bellBtn.addEventListener('click', function (e) {
      e.stopPropagation();
      closePopovers(bellPop);
      bellPop.classList.toggle('open');
    });

    function refreshNotif() {
      var count = (DB.notifications || []).filter(function (n) { return !n.read; }).length;
      var el = $('#notifCount');
      if (el) { el.textContent = count; el.style.display = count ? '' : 'none'; }
    }
    $$('#bellPopover [data-notif]').forEach(function (item) {
      item.addEventListener('click', function () {
        var n = DB.notifications[Number(item.getAttribute('data-notif'))];
        if (n) { n.read = true; item.classList.remove('unread'); refreshNotif(); saveDemo(); }
      });
    });
    var notifAll = $('#bellPopover [data-notif-all]');
    if (notifAll) notifAll.addEventListener('click', function (e) {
      e.stopPropagation();
      (DB.notifications || []).forEach(function (n) { n.read = true; });
      $$('#bellPopover .popover-item').forEach(function (el) { el.classList.remove('unread'); });
      refreshNotif();
      saveDemo();
      toast('全部通知已标记为已读', 'ok');
    });

    var userBtn = $('#userBtn'), userPop = $('#userPopover');
    if (userBtn && userPop) userBtn.addEventListener('click', function (e) {
      e.stopPropagation();
      closePopovers(userPop);
      userPop.classList.toggle('open');
    });
    $$('[data-user-go]').forEach(function (item) {
      item.addEventListener('click', function () {
        closePopovers();
        var action = item.getAttribute('data-user-go');
        if (action === 'logout') go('login.html');
        else if (action === 'masterdata') go('masterdata.html');
        else if (action === 'system') go('system.html');
        else if (action === 'help') openShortcuts();
        else if (action === 'audit') openAudit();
        else if (action === 'reset') {
          confirm({
            title: '重置演示数据',
            body: '将清除本机保存的演示改动（新增订单、报工、判定、记账等），并恢复初始演示数据。',
            okText: '确认重置',
            danger: true,
            onOk: resetDemo
          });
        }
      });
    });
    document.addEventListener('click', function (e) {
      if (!e.target.closest('.popover') && !e.target.closest('#bellBtn') && !e.target.closest('#userBtn') && !e.target.closest('#quickBtn')) {
        closePopovers();
      }
    });

    var gs = $('#globalSearch');
    if (gs) gs.addEventListener('click', openSearch);
    document.addEventListener('keydown', function (e) {
      if ((e.ctrlKey || e.metaKey) && String(e.key).toLowerCase() === 'k') {
        e.preventDefault();
        openSearch();
        return;
      }
      if (e.key === 'Escape') {
        closePopovers();
        var overlays = $$('.modal-mask.open').concat($$('.drawer-mask.open'));
        if (overlays.length) {
          overlays[overlays.length - 1].remove();
          unlockBody();
        }
        return;
      }
      var tag = (e.target.tagName || '').toLowerCase();
      if (tag === 'input' || tag === 'textarea' || tag === 'select' || e.target.isContentEditable) return;
      if (document.querySelector('.modal-mask.open, .drawer-mask.open')) return;
      if (e.key === '?') { e.preventDefault(); openShortcuts(); }
      else if (e.key === '/') {
        e.preventDefault();
        var input = document.querySelector('.tab-panel:not(.hidden) [data-tb-search]') || document.querySelector('[data-tb-search]');
        if (input) input.focus();
      }
    });
  }

  function closePopovers(except) {
    $$('.popover.open').forEach(function (p) { if (p !== except) p.classList.remove('open'); });
  }

  function openShortcuts() {
    closePopovers();
    modal({
      title: '键盘快捷键',
      body: '<div class="dl" style="grid-template-columns:148px 1fr">' +
        '<dt><span class="kbd">Ctrl</span> + <span class="kbd">K</span></dt><dd>全局搜索页面、单号、物料、序列号</dd>' +
        '<dt><span class="kbd">/</span></dt><dd>聚焦当前页表格搜索框</dd>' +
        '<dt><span class="kbd">?</span></dt><dd>打开本快捷键面板</dd>' +
        '<dt><span class="kbd">Esc</span></dt><dd>关闭弹窗、抽屉或下拉面板</dd>' +
        '<dt><span class="kbd">Enter</span></dt><dd>在搜索结果中打开第一条</dd>' +
      '</div>' +
      '<div class="note" style="margin-top:14px">提示：KPI 卡片、业务总图节点、通知均可下钻；表格支持每页条数切换、批量选择与导出。</div>',
      footer: '<div class="spacer"></div><button class="btn primary" data-close>知道了</button>'
    });
  }

  function openAudit() {
    closePopovers();
    var logs = (DB.audit || []).slice(0, 50);
    var body = logs.length
      ? '<div class="mb-12"><span class="tag ok">最近 ' + logs.length + ' 条</span><span class="hint" style="margin-left:8px">成功操作会自动记录</span></div><div id="auditList"></div>'
      : '<div class="empty">' + icon('fileText', 34) + '<div class="e-title">暂无操作记录</div><div class="e-sub">创建订单、报工、判定、记账后会自动记录</div></div>';
    drawer({
      title: '操作日志',
      sub: '本机演示操作记录 · 最近 50 条',
      copy: false,
      body: body,
      footer: '<button class="btn" data-close>关闭</button><div class="spacer"></div><button class="btn danger" id="auditClear">清空日志</button>',
      onMount: function (card, close) {
        if (logs.length) {
          timeline(card.querySelector('#auditList'), logs.map(function (l) {
            return { tone: 'ok', title: l.msg, time: l.time, body: '<span class="hint">' + esc(l.page || '') + '</span>' };
          }));
        }
        var clear = card.querySelector('#auditClear');
        if (clear) clear.addEventListener('click', function () {
          DB.audit = [];
          saveDemo();
          close();
          toast('操作日志已清空', 'info');
        });
      }
    });
  }

  function searchObjects(q) {
    if (!q) return [];
    var kw = q.toLowerCase();
    var out = [];
    function hit(s) { return String(s == null ? '' : s).toLowerCase().indexOf(kw) > -1; }
    function add(kind, title, meta, href) { out.push({ kind: kind, title: title, meta: meta, href: href }); }

    var P = DB.production || {};
    (P.workOrders || []).forEach(function (r) {
      if (hit(r.no) || hit(r.product) || hit(r.line)) add('工单', r.no, r.product + ' · ' + r.line + ' · ' + r.status, buildUrl('production.html', { q: r.no, mount: '#woTable' }));
    });
    var PU = DB.purchase || {};
    (PU.orders || []).forEach(function (r) {
      if (hit(r.no) || hit(r.supplier) || hit(r.material)) add('采购订单', r.no, r.supplier + ' · ' + r.material + ' · ' + r.status, buildUrl('purchase.html', { tab: 'orders', q: r.no }));
    });
    (PU.suppliers || []).forEach(function (r) {
      if (hit(r.name) || hit(r.code)) add('供应商', r.name, r.code + ' · ' + r.category, buildUrl('purchase.html', { tab: 'suppliers', q: r.name }));
    });
    var SA = DB.sales || {};
    (SA.orders || []).forEach(function (r) {
      if (hit(r.no) || hit(r.customer) || hit(r.product)) add('销售订单', r.no, r.customer + ' · ' + r.product, buildUrl('sales.html', { tab: 'orders', q: r.no }));
    });
    var IV = DB.inventory || {};
    (IV.stock || []).forEach(function (r) {
      if (hit(r.code) || hit(r.name)) add('库存物料', r.name, r.code + ' · ' + r.warehouse, buildUrl('inventory.html', { tab: 'stock', q: r.code }));
    });
    ((DB.trace && DB.trace.index) || []).forEach(function (r) {
      if (hit(r.serial) || hit(r.product) || hit(r.batch)) add('追溯序列号', r.serial, r.product + ' · ' + r.batch, buildUrl('trace.html', { serial: r.serial }));
    });
    ((DB.quality && DB.quality.issues) || []).forEach(function (r) {
      if (hit(r.no) || hit(r.defect)) add('质量问题', r.no, r.defect + ' · ' + r.status, buildUrl('quality.html', { q: r.no, mount: '#issueTable' }));
    });
    ((DB.quality && DB.quality.ncList) || []).forEach(function (r) {
      if (hit(r.no) || hit(r.batch) || hit(r.defect)) add('不合格品', r.no, r.batch + ' · ' + r.defect, buildUrl('quality.html', { q: r.no, mount: '#ncTable' }));
    });
    ((DB.collab && DB.collab.exceptions) || []).forEach(function (r) {
      if (hit(r.no) || hit(r.material) || hit(r.supplier)) add('交付异常', r.no, r.supplier + ' · ' + r.material, buildUrl('collab.html', { tab: 'exception', q: r.no }));
    });
    ((DB.manufacturing && DB.manufacturing.groupPlan) || []).forEach(function (r) {
      if (hit(r.no) || hit(r.customer) || hit(r.product)) add('集团计划', r.no, r.customer + ' · ' + r.product, buildUrl('manufacturing.html', { tab: 'group', q: r.no }));
    });
    ((DB.manufacturing && DB.manufacturing.instructions) || []).forEach(function (r) {
      if (hit(r.no) || hit(r.product)) add('生产指令', r.no, r.product + ' · ' + r.status, buildUrl('manufacturing.html', { tab: 'instruction', q: r.no }));
    });
    ((DB.u8c && DB.u8c.vouchers) || []).forEach(function (r) {
      if (hit(r.no) || hit(r.source)) add('U8C 凭证', r.no, r.source + ' · ' + r.status, buildUrl('system.html', { q: r.no, mount: '#u8cTable' }));
    });
    return out.slice(0, 40);
  }

  function openSearch() {
    if ($('.modal-mask.open')) return;
    var pages = [];
    NAV.forEach(function (g) { g.items.forEach(function (it) { pages.push({ title: it.title, group: g.group, href: it.href }); }); });
    var html = '<div class="search-box mb-12" style="width:100%">' + icon('search') +
      '<input class="input" id="searchInput" placeholder="搜索页面 / 工单 / 采购订单 / 物料 / 序列号…" autocomplete="off"></div>' +
      '<div id="searchResults" style="max-height:360px;overflow:auto"></div>' +
      '<div class="hint mt-12">输入编号可直接定位业务对象；Ctrl + K 唤起，Esc 关闭。</div>';
    var m = modal({ title: '全局搜索', body: html, footer: '<div class="spacer"></div><button class="btn" data-close>关闭</button>' });
    m.mask.dataset.search = '1';
    var input = $('#searchInput');
    var box = $('#searchResults');

    function render(q) {
      var pageList = pages.filter(function (i) { return !q || i.title.indexOf(q) > -1 || i.group.indexOf(q) > -1; });
      var objs = searchObjects(q);
      var out = '';
      if (pageList.length) {
        out += '<div class="hint" style="padding:2px 2px 6px">页面</div>' + pageList.map(function (i) {
          return '<a class="popover-item" style="display:block;border:1px solid var(--border);border-radius:4px;margin-bottom:6px" href="' + i.href + '">' +
            '<div class="pi-title">' + esc(i.title) + '</div><div class="pi-meta">' + esc(i.group) + '</div></a>';
        }).join('');
      }
      if (objs.length) {
        out += '<div class="hint" style="padding:10px 2px 6px">业务对象 · ' + objs.length + ' 条</div>' + objs.map(function (o) {
          return '<a class="popover-item" style="display:block;border:1px solid var(--border);border-radius:4px;margin-bottom:6px" href="' + o.href + '">' +
            '<div class="pi-title">' + esc(o.title) + ' <span class="tag outline" style="margin-left:4px">' + esc(o.kind) + '</span></div>' +
            '<div class="pi-meta">' + esc(o.meta) + '</div></a>';
        }).join('');
      }
      box.innerHTML = out || '<div class="table-empty">未找到匹配内容</div>';
    }
    render('');
    input.focus();
    input.addEventListener('input', function () { render(input.value.trim()); });
    input.addEventListener('keydown', function (e) {
      if (e.key === 'Enter') {
        var first = box.querySelector('a');
        if (first) {
          var target = first.getAttribute('href');
          startProgress();
          setTimeout(function () { location.href = target; }, 120);
        }
      }
    });
  }

  /* ---------------- 组件 ---------------- */
  var KPI_LINK = {
    '当班产出': 'analysis.html',
    '计划达成率': 'plan.html',
    '一次合格率 FTT': 'quality.html',
    '一次合格率': 'quality.html',
    '综合效率 OEE': 'equipment.html',
    '在制品 WIP': 'production.html',
    '平均节拍': 'analysis.html',
    '设备稼动率': 'equipment.html',
    '缺料工单': 'inventory.html?tab=alert',
    '采购在途': 'purchase.html?tab=orders',
    '待交付订单': 'collab.html?tab=delivery',
    '库存金额': 'inventory.html?tab=stock',
    '交付准时率': 'collab.html',
    '本月采购额': 'purchase.html',
    '本月销售额': 'sales.html',
    '应付余额': 'costing.html',
    'U8C 凭证同步': 'system.html',
    '本月入库金额': 'costing.html',
    '本月出库金额': 'costing.html',
    '期末结存': 'costing.html',
    '待记账单据': 'costing.html?tab=voucher',
    '在途订单': 'purchase.html?tab=orders',
    '待入库': 'inventory.html?tab=inbound',
    '待结算': 'purchase.html?tab=settlements',
    '待发货': 'sales.html?tab=orders',
    '待开票': 'sales.html?tab=invoices',
    '应收余额': 'sales.html',
    '库存预警': 'inventory.html?tab=alert',
    '待出库': 'inventory.html?tab=outbound',
    '库存周转': 'analysis.html',
    '集团总计划': 'manufacturing.html?tab=group',
    '生产指令单': 'manufacturing.html?tab=instruction',
    '投料齐套率': 'manufacturing.html?tab=feeding',
    '生产异常': 'manufacturing.html?tab=finished',
    '交付异常': 'collab.html?tab=exception',
    '待送检样品': 'collab.html?tab=sample',
    '在途送货': 'collab.html?tab=shipment',
    '待处理问题': 'quality.html?mount=%23issueTable',
    'DPU 下线缺陷率': 'quality.html',
    '当前报警': 'equipment.html?tab=alarm',
    '点检完成率': 'equipment.html?tab=check'
  };

  function kpi(mount, items, cols) {
    var m = el(mount); if (!m) return;
    var arr = items || [];
    if (cols === 2 || cols === 3) {
      m.classList.remove('grid-4');
      m.classList.add('grid-' + cols);
    } else if (!m.classList.contains('grid-4') && !m.classList.contains('grid-3') && !m.classList.contains('grid-2')) {
      m.classList.add('grid-4');
    }
    m.innerHTML = arr.map(function (k) {
      var d = k.delta;
      var dt = '';
      if (d != null) {
        var tr = k.trend || (String(d).charAt(0) === '-' ? 'down' : 'up');
        var arrow = tr === 'up' ? '↑' : tr === 'down' ? '↓' : '→';
        dt = '<div class="kpi-delta ' + tr + '">' + arrow + ' ' + esc(d) + (k.deltaNote ? ' <span class="muted">' + esc(k.deltaNote) + '</span>' : '') + '</div>';
      }
      var href = k.href || KPI_LINK[k.label];
      return '<div class="kpi ' + (k.tone || '') + '"' + (href ? ' data-href="' + esc(href) + '" title="查看明细"' : '') + '>' +
        '<div class="kpi-label">' + (k.icon ? icon(k.icon) : '') + esc(k.label) + '</div>' +
        '<div class="kpi-value">' + esc(k.value) + (k.unit ? '<span class="u">' + esc(k.unit) + '</span>' : '') + '</div>' +
        dt +
        (k.foot ? '<div class="kpi-foot">' + k.foot + '</div>' : '') +
      '</div>';
    }).join('');
    $$('[data-href]', m).forEach(function (card) {
      card.addEventListener('click', function () { go(card.getAttribute('data-href')); });
    });
  }

  function table(opts) {
    var m = el(opts.mount); if (!m) return;
    var cols = opts.columns || [];
    var rows = (opts.rows || []).slice();
    var pageSize = opts.pageSize || 8;
    var state = { page: 1, q: '', sortKey: opts.sortKey || '', dir: 1, selected: {} };
    var selectable = !!opts.selectable;
    var batchActions = opts.batchActions || (selectable && opts.exportName ? [{ act: 'export', label: '导出所选' }] : []);
    var keyOf = function (r, i) {
      if (typeof opts.rowKey === 'function') return String(opts.rowKey(r));
      if (opts.rowKey) return String(r[opts.rowKey]);
      return String(i);
    };
    var sizeOptions = [5, 10, 20];
    if (sizeOptions.indexOf(pageSize) === -1) { sizeOptions.push(pageSize); sizeOptions.sort(function (a, b) { return a - b; }); }

    m.innerHTML = '<div class="table-toolbar">' +
      (opts.search === false ? '' : '<div class="search-box">' + icon('search') + '<input class="input" data-tb-search placeholder="' + esc(opts.searchPlaceholder || '搜索…') + '"><button type="button" class="search-clear" data-tb-clear title="清除搜索">×</button></div>') +
      '<span class="count" data-tb-count></span>' +
      '<span class="selection" data-tb-selection style="display:none"></span>' +
      '<div class="spacer"></div>' +
      (opts.toolbar || '') +
      (opts.exportName ? '<button class="btn sm" data-tb-export>' + icon('download') + '导出</button>' : '') +
    '</div>' +
    '<div class="table-wrap" data-tb-wrap></div>' +
    '<div class="pager" data-tb-pager></div>';

    var searchInput = m.querySelector('[data-tb-search]');
    function toggleClear() {
      if (!searchInput) return;
      var box = searchInput.closest('.search-box');
      if (box) box.classList.toggle('has-value', !!searchInput.value);
    }
    if (searchInput) {
      toggleClear();
      searchInput.addEventListener('input', function () {
        state.q = this.value.trim();
        state.page = 1;
        toggleClear();
        render();
      });
      searchInput.addEventListener('keydown', function (e) {
        if (e.key === 'Escape') {
          this.value = ''; state.q = ''; state.page = 1; toggleClear(); render();
        }
      });
    }
    var clearBtn = m.querySelector('[data-tb-clear]');
    if (clearBtn) clearBtn.addEventListener('click', function () {
      if (!searchInput) return;
      searchInput.value = '';
      state.q = '';
      state.page = 1;
      toggleClear();
      render();
      searchInput.focus();
    });
    var exp = m.querySelector('[data-tb-export]');
    if (exp) exp.addEventListener('click', function () { exportCsv(opts.exportName, cols, getRows()); });

    function getRows() {
      var q = state.q.toLowerCase();
      var list = rows.filter(function (r) {
        if (!q) return true;
        var keys = opts.searchKeys || Object.keys(r);
        return keys.some(function (k) { return String(r[k] == null ? '' : r[k]).toLowerCase().indexOf(q) > -1; });
      });
      if (state.sortKey) {
        list.sort(function (a, b) {
          var x = a[state.sortKey], y = b[state.sortKey];
          if (x == null) return 1;
          if (y == null) return -1;
          if (typeof x === 'number' && typeof y === 'number') return (x - y) * state.dir;
          return String(x).localeCompare(String(y), 'zh-CN') * state.dir;
        });
      }
      return list;
    }

    function selectedRows() {
      return rows.filter(function (r, i) { return state.selected[keyOf(r, i)]; });
    }

    function updateSelection() {
      var bar = m.querySelector('[data-tb-selection]');
      if (!bar) return;
      var count = Object.keys(state.selected).length;
      if (!selectable || !count) { bar.style.display = 'none'; bar.innerHTML = ''; return; }
      bar.style.display = 'flex';
      bar.innerHTML = '已选 <b>' + count + '</b> 项' +
        '<button type="button" class="btn sm ghost" data-tb-unselect>取消选择</button>' +
        batchActions.map(function (a) {
          return '<button type="button" class="btn sm" data-batch="' + esc(a.act) + '">' + esc(a.label) + '</button>';
        }).join('');
      var un = bar.querySelector('[data-tb-unselect]');
      if (un) un.addEventListener('click', function () { state.selected = {}; render(); });
      bar.querySelectorAll('[data-batch]').forEach(function (btn) {
        btn.addEventListener('click', function () {
          var sel = selectedRows();
          if (!sel.length) return;
          var act = btn.getAttribute('data-batch');
          if (act === 'export') { exportCsv((opts.exportName || '导出') + '-所选', cols, sel); return; }
          if (opts.onBatch) opts.onBatch(act, sel);
          else toast('已对 ' + sel.length + ' 条记录执行「' + btn.textContent + '」', 'ok');
        });
      });
    }

    function render() {
      var list = getRows();
      var totalPages = Math.max(1, Math.ceil(list.length / pageSize));
      if (state.page > totalPages) state.page = totalPages;
      var start = (state.page - 1) * pageSize;
      var view = list.slice(start, start + pageSize);

      var head = selectable ? '<th class="check"><input type="checkbox" data-check-all title="选择本页"></th>' : '';
      head += cols.map(function (c, ci) {
        var sortable = c.key && c.sortable !== false;
        var ico = c.key === state.sortKey ? (state.dir > 0 ? '▲' : '▼') : '⇅';
        return '<th data-col="' + ci + '" class="' + (c.cls === 'num' ? 'num' : '') + (sortable ? ' sortable' : '') + '">' +
          esc(c.label) + (sortable ? ' <span class="sort-ico">' + ico + '</span>' : '') + '</th>';
      }).join('');
      var html = '<table class="table"><thead><tr>' + head + '</tr></thead><tbody>';

      if (!view.length) {
        html += '<tr><td colspan="' + (cols.length + (selectable ? 1 : 0)) + '"><div class="table-empty">' +
          esc(opts.empty || (state.q ? '未找到匹配记录，请调整搜索条件' : '暂无数据')) + '</div></td></tr>';
      } else {
        view.forEach(function (r, i) {
          var rk = keyOf(r, start + i);
          var checked = !!state.selected[rk];
          var rowCls = (opts.rowClass ? opts.rowClass(r) : '') + (checked ? ' selected' : '');
          html += '<tr data-idx="' + i + '" class="' + (opts.onRowClick ? 'clickable ' : '') + rowCls + '">';
          if (selectable) {
            html += '<td class="check"><input type="checkbox" data-row-key="' + esc(rk) + '"' + (checked ? ' checked' : '') + '></td>';
          }
          html += cols.map(function (c) {
            var v = c.formatter ? c.formatter(r) : esc(r[c.key]);
            return '<td class="' + (c.cls || '') + '">' + (v == null || v === '' ? '<span class="muted">—</span>' : v) + '</td>';
          }).join('') + '</tr>';
        });
      }
      html += '</tbody></table>';
      m.querySelector('[data-tb-wrap]').innerHTML = html;
      m.querySelector('[data-tb-count]').textContent = '共 ' + list.length + ' 条';
      m.querySelector('[data-tb-pager]').innerHTML =
        '<span>第 ' + state.page + ' / ' + totalPages + ' 页</span>' +
        '<span class="hint">每页</span>' +
        '<select class="pager-size" data-tb-size>' +
          sizeOptions.map(function (n) { return '<option value="' + n + '"' + (n === pageSize ? ' selected' : '') + '>' + n + '</option>'; }).join('') +
        '</select>' +
        '<div class="spacer"></div>' +
        '<button data-page="prev" ' + (state.page <= 1 ? 'disabled' : '') + '>‹</button>' +
        '<button class="active">' + state.page + '</button>' +
        '<button data-page="next" ' + (state.page >= totalPages ? 'disabled' : '') + '>›</button>';

      m.querySelectorAll('th.sortable').forEach(function (th) {
        th.addEventListener('click', function () {
          var key = cols[Number(th.getAttribute('data-col'))].key;
          if (state.sortKey === key) state.dir = -state.dir;
          else { state.sortKey = key; state.dir = 1; }
          render();
        });
      });
      m.querySelectorAll('.pager [data-page]').forEach(function (b) {
        b.addEventListener('click', function () {
          state.page += (b.getAttribute('data-page') === 'next' ? 1 : -1);
          render();
        });
      });
      var sizeSel = m.querySelector('[data-tb-size]');
      if (sizeSel) sizeSel.addEventListener('change', function () {
        pageSize = Number(this.value) || pageSize;
        state.page = 1;
        render();
      });
      if (opts.onRowClick) {
        m.querySelectorAll('tbody tr[data-idx]').forEach(function (tr) {
          tr.addEventListener('click', function (e) {
            if (e.target.closest('input,button,a,select,[data-act]')) return;
            opts.onRowClick(view[Number(tr.getAttribute('data-idx'))]);
          });
        });
      }
      if (opts.onAction) {
        m.querySelectorAll('[data-act]').forEach(function (btn) {
          btn.addEventListener('click', function (e) {
            e.stopPropagation();
            var tr = btn.closest('tr');
            var idx = tr ? Number(tr.getAttribute('data-idx')) : 0;
            opts.onAction(btn.getAttribute('data-act'), view[idx]);
          });
        });
      }
      if (selectable) {
        var viewKeys = view.map(function (r, i) { return keyOf(r, start + i); });
        var picked = viewKeys.filter(function (k) { return state.selected[k]; }).length;
        var all = m.querySelector('[data-check-all]');
        if (all) {
          all.checked = viewKeys.length > 0 && picked === viewKeys.length;
          all.indeterminate = picked > 0 && picked < viewKeys.length;
          all.addEventListener('change', function () {
            viewKeys.forEach(function (k) {
              if (all.checked) state.selected[k] = true; else delete state.selected[k];
            });
            render();
          });
        }
        m.querySelectorAll('input[data-row-key]').forEach(function (cb) {
          cb.addEventListener('change', function () {
            var k = cb.getAttribute('data-row-key');
            if (cb.checked) state.selected[k] = true; else delete state.selected[k];
            var tr = cb.closest('tr');
            if (tr) tr.classList.toggle('selected', cb.checked);
            updateSelection();
            if (opts.onSelect) opts.onSelect(selectedRows());
          });
        });
      }
      updateSelection();
    }
    render();
    return {
      refresh: function (newRows) {
        if (newRows) rows = newRows.slice();
        var valid = {};
        rows.forEach(function (r, i) { valid[keyOf(r, i)] = true; });
        Object.keys(state.selected).forEach(function (k) { if (!valid[k]) delete state.selected[k]; });
        render();
      },
      selected: selectedRows,
      clearSelection: function () { state.selected = {}; render(); }
    };
  }

  function exportCsv(name, cols, rows) {
    var usable = cols.filter(function (c) { return c.key; });
    var head = usable.map(function (c) { return '"' + String(c.label).replace(/"/g, '""') + '"'; }).join(',');
    var body = rows.map(function (r) {
      return usable.map(function (c) {
        var v = c.csv ? c.csv(r) : r[c.key];
        if (v == null) v = '';
        return '"' + String(v).replace(/<[^>]+>/g, '').replace(/"/g, '""') + '"';
      }).join(',');
    }).join('\r\n');
    var blob = new Blob(['\ufeff' + head + '\r\n' + body], { type: 'text/csv;charset=utf-8;' });
    var a = document.createElement('a');
    a.href = URL.createObjectURL(blob);
    a.download = (name || 'export') + '_' + new Date().toISOString().slice(0, 10) + '.csv';
    document.body.appendChild(a);
    a.click();
    setTimeout(function () { URL.revokeObjectURL(a.href); a.remove(); }, 100);
    toast('已导出 ' + rows.length + ' 条记录', 'ok');
  }

  function ring(pct, label, tone, size) {
    var p = Math.round(pct || 0);
    return '<div class="ring ' + (tone || pctTone(p)) + (size === 'sm' ? ' sm' : '') + '" style="--p:' + p + '">' +
      '<div class="ring-val">' + p + '%' + (label ? '<small>' + esc(label) + '</small>' : '') + '</div></div>';
  }

  function bars(mount, items, opts) {
    var m = el(mount); if (!m) return;
    opts = opts || {};
    var arr = items || [];
    var max = opts.max || Math.max.apply(null, arr.map(function (i) { return Number(i.v) || 0; }).concat([1]));
    m.className = 'bars ' + (m.className || '').trim();
    m.innerHTML = arr.map(function (it, i) {
      var p = Math.max(0, Math.min(100, (Number(it.v) || 0) / max * 100));
      var tone = it.tone || (opts.accentFirst && i === 0 ? 'accent' : '');
      return '<div class="bar-row"' + (it.href ? ' data-href="' + esc(it.href) + '" title="查看明细"' : '') + '><span class="bar-name" title="' + esc(it.name) + '">' + esc(it.name) + '</span>' +
        '<div class="bar-track"><div class="bar-fill ' + tone + '" style="width:' + p + '%"></div></div>' +
        '<span class="bar-val">' + esc(it.v) + esc(opts.unit || '') + '</span></div>';
    }).join('');
    $$('[data-href]', m).forEach(function (row) {
      row.addEventListener('click', function () { go(row.getAttribute('data-href')); });
    });
  }

  function vbars(mount, items, opts) {
    var m = el(mount); if (!m) return;
    opts = opts || {};
    var arr = items || [];
    var max = opts.max || Math.max.apply(null, arr.map(function (i) { return Number(i.v) || 0; }).concat([1]));
    m.classList.add('vbars');
    m.innerHTML = arr.map(function (it) {
      var p = Math.max(2, (Number(it.v) || 0) / max * 100);
      return '<div class="vbar ' + (it.tone || '') + '"' + (it.href ? ' data-href="' + esc(it.href) + '"' : '') + ' title="' + esc(it.h) + '：' + esc(it.v) + esc(opts.unit || '') + '">' +
        '<div class="vb-col" style="height:' + p + '%"></div><span class="vb-lab">' + esc(it.h) + '</span></div>';
    }).join('');
    $$('[data-href]', m).forEach(function (bar) {
      bar.addEventListener('click', function () { go(bar.getAttribute('data-href')); });
    });
  }

  function sparkline(mount, values, tone, opts) {
    var m = el(mount); if (!m) return;
    opts = opts || {};
    var arr = (values || []).map(Number);
    if (!arr.length) { m.innerHTML = ''; return; }
    var max = Math.max.apply(null, arr), min = Math.min.apply(null, arr);
    var span = max - min || 1;
    var pts = arr.map(function (v, i) {
      var x = arr.length === 1 ? 50 : i / (arr.length - 1) * 100;
      var y = 30 - (v - min) / span * 26;
      return x.toFixed(2) + ',' + y.toFixed(2);
    });
    var color = tone === 'accent' ? '#E07B1A' : tone === 'danger' ? '#B23A2E' : tone === 'ok' ? '#1B7F55' : '#12495F';
    var area = '0,34 ' + pts.join(' ') + ' 100,34';
    m.innerHTML = '<svg class="spark" viewBox="0 0 100 34" preserveAspectRatio="none">' +
      '<polygon points="' + area + '" fill="' + color + '" opacity=".08"></polygon>' +
      '<polyline points="' + pts.join(' ') + '" fill="none" stroke="' + color + '" stroke-width="1.8" stroke-linejoin="round" stroke-linecap="round"></polyline>' +
    '</svg>';
  }

  function timeline(mount, items) {
    var m = el(mount); if (!m) return;
    m.classList.add('timeline');
    m.innerHTML = (items || []).map(function (it) {
      return '<div class="tl-item ' + (it.tone || '') + '"><span class="tl-dot"></span>' +
        '<div class="tl-head"><span class="tl-title">' + esc(it.title) + '</span><span class="tl-time">' + esc(it.time || '') + '</span></div>' +
        (it.body ? '<div class="tl-body">' + it.body + '</div>' : '') +
      '</div>';
    }).join('');
  }

  function flow(mount, nodes) {
    var m = el(mount); if (!m) return;
    m.classList.add('flow-chain');
    m.innerHTML = (nodes || []).map(function (n, i) {
      var cells = (n.cells || []).map(function (c) {
        return '<div><div class="fn-k">' + esc(c.k) + '</div><div class="fn-v">' + esc(c.v) + '</div></div>';
      }).join('');
      var inner = '<div class="fn-name">' + (n.icon ? icon(n.icon, 14) : '') + esc(n.name) + '</div>' +
        '<div class="fn-meta">' + esc(n.meta || '') + '</div>' +
        '<div class="fn-grid">' + cells + '</div>';
      var node = n.href
        ? '<a class="flow-node ' + (n.tone || '') + '" href="' + esc(n.href) + '" title="进入' + esc(n.name) + '">' + inner + '</a>'
        : '<div class="flow-node ' + (n.tone || '') + '">' + inner + '</div>';
      return (i ? '<span class="flow-arrow">' + icon('chevronRight', 16) + '</span>' : '') + node;
    }).join('');
  }

  function stationChain(mount, items) {
    var m = el(mount); if (!m) return;
    m.classList.add('station-chain');
    m.innerHTML = (items || []).map(function (s) {
      return '<div class="stn ' + (s.tone || '') + '">' +
        '<div class="stn-name">' + esc(s.title) + '</div>' +
        '<div class="stn-speed ' + (s.valueTone || '') + '">' + esc(s.value) + (s.unit ? '<span class="hint"> ' + esc(s.unit) + '</span>' : '') + '</div>' +
        '<div class="stn-meta">' + esc(s.meta || '') + '</div>' +
      '</div>';
    }).join('');
  }

  function opFlow(mount, steps) {
    var m = el(mount); if (!m) return;
    m.classList.add('op-flow');
    m.innerHTML = (steps || []).map(function (s) {
      return '<div class="op-step ' + (s.state || '') + '">' +
        '<span class="st-no">' + esc(s.no) + '</span>' +
        '<span><span>' + esc(s.name) + '</span>' + (s.meta ? '<br><span class="st-meta">' + esc(s.meta) + '</span>' : '') + '</span>' +
      '</div>';
    }).join('');
  }

  function kanban(mount, columns) {
    var m = el(mount); if (!m) return;
    m.classList.add('board');
    m.innerHTML = (columns || []).map(function (c) {
      return '<div class="board-col"><div class="board-col-head"><span class="led ' + (c.tone || 'primary') + '"></span>' + esc(c.title) +
        '<span class="count">' + (c.cards || []).length + '</span></div>' +
        (c.cards || []).map(function (card) {
          return '<div class="board-card ' + (card.cls || '') + '">' +
            '<div class="bc-no">' + esc(card.no) + '</div>' +
            '<div class="bc-name">' + esc(card.name) + '</div>' +
            '<div class="bc-meta">' + (card.meta || []).map(function (x) { return '<span>' + esc(x) + '</span>'; }).join('') + '</div>' +
          '</div>';
        }).join('') +
      '</div>';
    }).join('');
  }

  function ledBoard(mount, items, date) {
    var m = el(mount); if (!m) return;
    m.classList.add('led-board');
    m.innerHTML = (items || []).map(function (it, i) {
      return (i ? '<span class="led-sep"></span>' : '') +
        '<div><div class="led-k">' + esc(it.k) + '</div><div class="led-v ' + (it.tone || '') + '">' + esc(it.v) + '</div></div>';
    }).join('') + (date ? '<span class="led-sep"></span><div><div class="led-k">数据时间</div><div class="led-date">' + esc(date) + '</div></div>' : '');
  }

  function dl(mount, pairs, width) {
    var m = el(mount); if (!m) return;
    m.classList.add('dl');
    if (width) m.style.gridTemplateColumns = width + ' 1fr';
    m.innerHTML = (pairs || []).map(function (p) {
      return '<dt>' + esc(p[0]) + '</dt><dd>' + (p[2] === 'html' ? p[1] : esc(p[1])) + '</dd>';
    }).join('');
  }

  function statStrip(mount, items) {
    var m = el(mount); if (!m) return;
    m.classList.add('stat-strip');
    m.innerHTML = (items || []).map(function (it) {
      return '<div class="stat-cell"><div class="sc-k">' + esc(it.k) + '</div>' +
        '<div class="sc-v" style="' + (it.color ? 'color:' + it.color : '') + '">' + esc(it.v) + (it.unit ? '<span class="u">' + esc(it.unit) + '</span>' : '') + '</div>' +
        (it.s ? '<div class="sc-s">' + esc(it.s) + '</div>' : '') + '</div>';
    }).join('');
  }

  function gantt(mount, rows, axis) {
    var m = el(mount); if (!m) return;
    m.classList.add('gantt');
    m.innerHTML = (rows || []).map(function (r) {
      return '<div class="gantt-row"><span class="gantt-lab" title="' + esc(r.label) + '">' + esc(r.label) + '</span>' +
        '<div class="gantt-track"><div class="gantt-bar ' + (r.tone || '') + '" style="left:' + (r.start || 0) + '%;width:' + Math.max(2, r.width || 0) + '%">' + esc(r.text || '') + '</div></div>' +
        '<span class="gantt-val">' + esc(r.value || '') + '</span></div>';
    }).join('') +
    (axis ? '<div class="gantt-axis"><span></span><div class="axis-track">' + axis.map(function (a) { return '<span>' + esc(a) + '</span>'; }).join('') + '</div><span></span></div>' : '');
  }

  function empty(mount, title, sub, iconName) {
    var m = el(mount); if (!m) return;
    m.className = 'empty';
    m.innerHTML = icon(iconName || 'box', 34) + '<div class="e-title">' + esc(title || '暂无数据') + '</div>' + (sub ? '<div class="e-sub">' + esc(sub) + '</div>' : '');
  }

  /* ---------------- 浮层 ---------------- */
  function lockBody() { document.body.style.overflow = 'hidden'; }
  function unlockBody() {
    if (!document.querySelector('.modal-mask.open, .drawer-mask.open')) document.body.style.overflow = '';
  }

  function copyText(text) {
    return new Promise(function (resolve) {
      function fallback() {
        try {
          var ta = document.createElement('textarea');
          ta.value = text;
          ta.style.position = 'fixed';
          ta.style.opacity = '0';
          document.body.appendChild(ta);
          ta.select();
          var ok = document.execCommand('copy');
          ta.remove();
          resolve(ok);
        } catch (e) { resolve(false); }
      }
      if (navigator.clipboard && navigator.clipboard.writeText) {
        navigator.clipboard.writeText(text).then(function () { resolve(true); }, fallback);
      } else fallback();
    });
  }

  function modal(opts) {
    opts = opts || {};
    var mask = document.createElement('div');
    mask.className = 'modal-mask open';
    mask.innerHTML = '<div class="modal-card ' + (opts.wide ? 'wide' : '') + '">' +
      '<div class="modal-head"><h3>' + esc(opts.title || '') + '</h3><div class="spacer"></div>' +
      '<button class="icon-btn" data-close aria-label="关闭">' + icon('x') + '</button></div>' +
      '<div class="modal-body">' + (opts.body || '') + '</div>' +
      '<div class="modal-foot">' + (opts.footer || '<div class="spacer"></div><button class="btn" data-close>关闭</button>') + '</div>' +
    '</div>';
    document.body.appendChild(mask);
    lockBody();
    function close() { mask.remove(); unlockBody(); if (opts.onClose) opts.onClose(); }
    mask.addEventListener('click', function (e) { if (e.target === mask || e.target.closest('[data-close]')) close(); });
    mask.addEventListener('keydown', function (e) {
      if (e.key === 'Enter' && e.target && e.target.tagName === 'INPUT' && !e.shiftKey) {
        var primary = mask.querySelector('.modal-foot .btn.primary');
        if (primary) { e.preventDefault(); primary.click(); }
      }
    });
    if (opts.onMount) opts.onMount(mask.querySelector('.modal-card'), close);
    setTimeout(function () {
      var first = mask.querySelector('.modal-body input:not([type="hidden"]), .modal-body textarea, .modal-body select');
      if (first && !first.disabled) { try { first.focus(); } catch (e) {} }
    }, 40);
    return { mask: mask, close: close, el: mask.querySelector('.modal-card') };
  }

  function drawer(opts) {
    opts = opts || {};
    var mask = document.createElement('div');
    mask.className = 'drawer-mask open';
    mask.innerHTML = '<aside class="drawer">' +
      '<div class="drawer-head"><div><div style="font-size:15px;font-weight:600">' + esc(opts.title || '') + '</div>' +
      (opts.sub ? '<div class="hint" style="margin-top:2px">' + opts.sub + '</div>' : '') + '</div><div class="spacer"></div>' +
      (opts.copy === false ? '' : '<button class="icon-btn" data-copy-title title="复制单号">' + icon('copy') + '</button>') +
      '<button class="icon-btn" data-close aria-label="关闭">' + icon('x') + '</button></div>' +
      '<div class="drawer-body">' + (opts.body || '') + '</div>' +
      (opts.footer ? '<div class="drawer-foot">' + opts.footer + '</div>' : '') +
    '</aside>';
    document.body.appendChild(mask);
    lockBody();
    function close() { mask.remove(); unlockBody(); if (opts.onClose) opts.onClose(); }
    mask.addEventListener('click', function (e) { if (e.target === mask || e.target.closest('[data-close]')) close(); });
    var copyBtn = mask.querySelector('[data-copy-title]');
    if (copyBtn) copyBtn.addEventListener('click', function () {
      copyText(opts.title || '').then(function (ok) {
        toast(ok ? '已复制：' + opts.title : '复制失败，请手动选择文本', ok ? 'ok' : 'warn');
      });
    });
    if (opts.onMount) opts.onMount(mask.querySelector('.drawer'), close);
    return { mask: mask, close: close, el: mask.querySelector('.drawer') };
  }

  function confirm(opts) {
    opts = opts || {};
    return modal({
      title: opts.title || '确认操作',
      body: '<div style="font-size:13px;line-height:1.8">' + (opts.body || '') + '</div>' +
        (opts.note ? '<div class="note warn" style="margin-top:12px">' + opts.note + '</div>' : ''),
      footer: '<div class="spacer"></div><button class="btn" data-close>取消</button><button class="btn ' + (opts.danger ? 'danger' : 'primary') + '" data-ok>' + esc(opts.okText || '确定') + '</button>',
      onMount: function (card, close) {
        card.querySelector('[data-ok]').addEventListener('click', function () {
          close();
          if (opts.onOk) opts.onOk();
        });
      }
    });
  }

  function toast(msg, tone, action) {
    if (tone === 'ok' && window.DB) {
      try {
        DB.audit = DB.audit || [];
        DB.audit.unshift({ time: nowStr(), page: (PAGE_META[currentPage()] || {}).title || '', msg: msg });
        if (DB.audit.length > 80) DB.audit.length = 80;
      } catch (e) {}
    }
    var wrap = $('.toast-wrap');
    if (!wrap) {
      wrap = document.createElement('div');
      wrap.className = 'toast-wrap';
      document.body.appendChild(wrap);
    }
    var t = document.createElement('div');
    t.className = 'toast ' + (tone || 'info');
    var ico = tone === 'ok' ? 'check' : tone === 'warn' ? 'alert' : tone === 'danger' ? 'alert' : 'activity';
    t.innerHTML = icon(ico) + '<span>' + esc(msg) + '</span>' +
      (action && action.label ? '<button type="button" class="toast-action">' + esc(action.label) + '</button>' : '');
    wrap.appendChild(t);
    var actBtn = t.querySelector('.toast-action');
    if (actBtn) actBtn.addEventListener('click', function (e) {
      e.stopPropagation();
      t.remove();
      if (action.onClick) action.onClick();
    });
    setTimeout(function () {
      t.style.transition = 'opacity .2s ease, transform .2s ease';
      t.style.opacity = '0';
      t.style.transform = 'translateY(6px)';
      setTimeout(function () { t.remove(); }, 220);
    }, 3600);
  }

  /* ---------------- 交互绑定 ---------------- */
  function bindTabs(root) {
    root = root || document;
    var boxes;
    if (root.nodeType === 1 && root.hasAttribute('data-tabs')) boxes = [root];
    else boxes = $$('[data-tabs]', root);
    boxes.forEach(function (box) {
      var tabs = $$('.tab', box);
      tabs.forEach(function (tab) {
        tab.addEventListener('click', function () {
          tabs.forEach(function (t) { t.classList.remove('active'); });
          tab.classList.add('active');
          var target = tab.getAttribute('data-tab');
          $$('.tab-panel', box.parentNode).forEach(function (p) {
            p.classList.toggle('hidden', p.getAttribute('data-panel') !== target);
          });
        });
      });
    });
  }

  function bindSeg(root, callback) {
    root = root || document;
    var boxes;
    if (root.nodeType === 1 && (root.hasAttribute('data-seg') || root.classList.contains('btn-group'))) {
      boxes = [root];
    } else {
      boxes = $$('[data-seg]', root);
    }
    boxes.forEach(function (box) {
      var btns = $$('button', box);
      btns.forEach(function (b) {
        b.addEventListener('click', function () {
          btns.forEach(function (x) { x.classList.remove('active'); });
          b.classList.add('active');
          if (callback) callback(b.getAttribute('data-value'), box);
        });
      });
    });
  }

  function live(fn, ms) {
    var timer = setInterval(function () {
      if (!document.hidden) fn();
    }, ms || 30000);
    return function () { clearInterval(timer); };
  }

  function init() {
    restoreDemo();
    buildNav();
    buildTopbar();
    bindTabs(document);
    setTimeout(applyQuery, 30);
    if ($('#appTopbar')) {
      startProgress();
      setTimeout(finishProgress, 260);
    }
    window.addEventListener('pagehide', saveDemo);
    document.addEventListener('visibilitychange', function () { if (document.hidden) saveDemo(); });
    window.addEventListener('pageshow', function () { var bar = $('#pageProgress'); if (bar) bar.remove(); });
    document.addEventListener('click', function (e) {
      var a = e.target.closest ? e.target.closest('a[href]') : null;
      if (!a) return;
      if (a.target === '_blank' || a.hasAttribute('download')) return;
      if (e.ctrlKey || e.metaKey || e.shiftKey || e.altKey || e.button !== 0) return;
      var href = a.getAttribute('href');
      if (!href || href.charAt(0) === '#' || href.indexOf('javascript:') === 0) return;
      if (!/\.html(\?|$)/.test(href)) return;
      e.preventDefault();
      startProgress();
      setTimeout(function () { location.href = href; }, 120);
    });
    document.documentElement.classList.add('mes-ready');
  }

  /* ---------------- 对外联动 API ---------------- */
  window.MES = {
    query: query,
    url: buildUrl,
    go: go,
    save: saveDemo,
    reset: resetDemo,
    clearQuery: clearQuery,
    applySearch: applySearch,
    activateTab: activateTab,
    searchObjects: searchObjects
  };

  /* ---------------- 导出 ---------------- */
  window.MESUI = {
    icon: icon,
    icons: ICONS,
    tag: tag,
    toneOf: toneOf,
    progress: progress,
    pctTone: pctTone,
    invalid: invalid,
    kpi: kpi,
    table: table,
    bars: bars,
    vbars: vbars,
    sparkline: sparkline,
    ring: ring,
    timeline: timeline,
    flow: flow,
    stationChain: stationChain,
    opFlow: opFlow,
    kanban: kanban,
    ledBoard: ledBoard,
    dl: dl,
    statStrip: statStrip,
    gantt: gantt,
    empty: empty,
    modal: modal,
    drawer: drawer,
    confirm: confirm,
    toast: toast,
    exportCsv: exportCsv,
    bindTabs: bindTabs,
    bindSeg: bindSeg,
    live: live,
    esc: esc,
    num: num,
    now: nowStr,
    refs: { $: $, $$: $$ },
    nav: NAV,
    pageMeta: PAGE_META
  };
  window.$ = $;
  window.$$ = $$;

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', init);
  } else {
    init();
  }
})();
