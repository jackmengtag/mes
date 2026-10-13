-- ----------------------------
-- MES 集成定时任务（领域事件投递 / 消息重试与死信 / 周期对账）
-- 依赖若依 quartz：任务类 com.ruoyi.quartz.task.MesIntegrationTask
-- 执行前请先执行 quartz.sql 初始化调度表
-- ----------------------------
delete from sys_job where job_id in (3001, 3002, 3003);

insert into sys_job values(3001, 'MES领域事件投递', 'DEFAULT', 'mesIntegrationTask.dispatchEvents()', '0/30 * * * * ?', '3', '1', '0', 'admin', sysdate(), '', null, '每30秒投递本地事件表中待发送的领域事件');
insert into sys_job values(3002, 'MES集成消息重试', 'DEFAULT', 'mesIntegrationTask.retryMessages()', '0 0/5 * * * ?', '3', '1', '0', 'admin', sysdate(), '', null, '每5分钟重试失败的出站消息，超过上限进入死信并生成异常单');
insert into sys_job values(3003, 'MES周期对账',    'DEFAULT', 'mesIntegrationTask.reconcile()',     '0 0 * * * ?',  '3', '1', '0', 'admin', sysdate(), '', null, '每小时对 ERP / WMS / 设备执行一次对账（日终由调度叠加）');
