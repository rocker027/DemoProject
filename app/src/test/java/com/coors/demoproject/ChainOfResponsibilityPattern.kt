package com.coors.demoproject

/**
 * 責任鍊模式
 * 案例：
 * 例子：学生会经费申请
重点：1 个请求会在 n 个处理器组成的处理器链上传递

以学生会经费申请会例，学生会会有一些日常开销以及活动开支，
需要向学院的学生会基金申请经费，
如果金额在 100 元之内，由分部长审批；
如果金额在 100 到 500 元之间，由会长审批；
如果金额在 500 到 1000 元之间，由学院辅导员审批；
而如果金额超过 1000 元，则默认打回申请。
像这种需要一层层往后传递请求的情况，非常适合采用责任链模式来设计程序：

作者：GitLqr
链接：https://juejin.cn/post/7100562422376693797
来源：稀土掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

 */

/**
 * 經費申請事件
 */
data class ApplyEvent(val amount: Int, val desc: String)

/**
 * 經費申請事件处理器
 */
interface ApplyHandler {
    val successor: ApplyHandler?
    fun handle(event: ApplyEvent) : String?
}

/**
 * 分部长审批
 */
class BranchManagerHandler(override val successor: ApplyHandler?) : ApplyHandler {
    override fun handle(event: ApplyEvent) : String? = if (event.amount in 100..500) {
        "分部长审批通过"
    } else {
        successor?.handle(event)
    }
}

/**
 * 会长审批
 */
class GeneralManagerHandler(override val successor: ApplyHandler?) : ApplyHandler {
    override fun handle(event: ApplyEvent) : String? = if (event.amount in 500..1000) {
        "会长审批通过"
    } else {
        successor?.handle(event)
    }
}

/**
 * 学院辅导员审批
 */
class CollegeCounselorHandler(override val successor: ApplyHandler?) : ApplyHandler {
    override fun handle(event: ApplyEvent) : String? = if (event.amount in 1000..Int.MAX_VALUE) {
        "学院辅导员审批通过"
    } else {
        successor?.handle(event)
    }
}

