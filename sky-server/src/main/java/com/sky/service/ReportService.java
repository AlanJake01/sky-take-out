package com.sky.service;

import com.sky.vo.TurnoverReportVO;

import java.time.LocalDate;

/**
 * ClassName:ReportService
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/12下午8:43
 * @Version:
 */
public interface ReportService {

    TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end);
}
