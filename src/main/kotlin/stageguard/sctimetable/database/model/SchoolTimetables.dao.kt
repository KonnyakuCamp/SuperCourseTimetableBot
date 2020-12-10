package stageguard.sctimetable.database.model

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import stageguard.sctimetable.service.TimeProviderService

/**
 * SchoolTimetables存储每个学校的某个学期的时间作息表
 *
 * 它是一个object而不是class，因为每一个用户是一个表格内的项目。
 **/
object SchoolTimetables : IntIdTable() {
    /**
     * 学校ID
     **/
    val schoolId: Column<Int> = integer("schoolId")
    /**
     * 学校名称
     **/
    val schoolName: Column<String> = varchar("schoolName", 50)
    /**
     * 这个时间表的开始年份
     **/
    val beginYear: Column<Int> = integer("beginYear")
    /**
     * 这个时间表对应的学期
     **/
    val semester: Column<Int> = integer("semester")
    /**
     * SchoolTimetables存储每个学校的某个学期的时间作息表
     *
     * 它的格式是```section.startHour:startMinute-endHour:endMinute|...```
     *
     * 例如：```1.08:10-08:55|2.09:00-09:45|...```
     **/
    val scheduledTimeList: Column<String> = varchar("timeList", 200)
    /**
     * 以下两个属性用于推断当前是这个学期的第几个周。
     *
     * 通过这个学校添加到数据库的时间和第一个用户指定的当前是第几周来推算任意时间的周数
     *
     * 计算工作由[TimeProviderService]执行
     **/
    val unixTimeWhenAdd: Column<Long> = long("unixTimeWhenAdd")
    val weekPeriodWhenAdd: Column<Int> = integer("weekPeriodWhenAdd")
}