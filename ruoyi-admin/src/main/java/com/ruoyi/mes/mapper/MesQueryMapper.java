package com.ruoyi.mes.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.mes.domain.MesPurchaseOrder;
import com.ruoyi.mes.domain.MesSalesOrder;
import com.ruoyi.mes.domain.MesWorkOrder;
import com.ruoyi.mes.domain.MesStock;
import com.ruoyi.mes.domain.MesTrace;
import com.ruoyi.mes.domain.MesReport;
import com.ruoyi.mes.domain.MesQualityIssue;

/**
 * MES 跨模块查询（业务联动与追溯）
 *
 * @author ruoyi
 */
public interface MesQueryMapper
{
    MesPurchaseOrder selectPurchaseByCode(@Param("orderCode") String orderCode);

    MesSalesOrder selectSalesByCode(@Param("orderCode") String orderCode);

    MesWorkOrder selectWorkOrderByCode(@Param("orderCode") String orderCode);

    MesStock selectStockByKey(@Param("materialCode") String materialCode,
                              @Param("warehouseCode") String warehouseCode,
                              @Param("batchNo") String batchNo);

    List<MesStock> selectStockByMaterial(@Param("materialCode") String materialCode,
                                         @Param("warehouseCode") String warehouseCode);

    MesTrace selectTraceByKeyword(@Param("keyword") String keyword);

    List<MesTrace> selectTraceListByBatch(@Param("batchNo") String batchNo);

    List<MesReport> selectReportsByOrder(@Param("orderCode") String orderCode);

    List<MesQualityIssue> selectIssuesByKeyword(@Param("keyword") String keyword);
}
