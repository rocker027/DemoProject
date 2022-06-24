package com.coors.demoproject

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ChainOfResponsibilityPatternTest : StringSpec({
    "test apply 150 amount success" {
        val chain = BranchManagerHandler(GeneralManagerHandler(CollegeCounselorHandler(null)))
        val result = chain.handle(ApplyEvent(150, "test"))
        result shouldBe "分部长审批通过"
    }

    "test apply 50 amount failed" {
        val chain = BranchManagerHandler(GeneralManagerHandler(CollegeCounselorHandler(null)))
        val result = chain.handle(ApplyEvent(50, "test"))
        result shouldBe null
    }

    "test apply 550 amount success" {
        val chain = BranchManagerHandler(GeneralManagerHandler(CollegeCounselorHandler(null)))
        val result = chain.handle(ApplyEvent(550, "test"))
        result shouldBe "分部长审批通过"
    }

    "test apply 600 amount success" {
        val groupLeader = {
            val defineAt: (ApplyEvent) -> Boolean = {
                it.amount in 100..500
            }
            val handler: (ApplyEvent) -> String? = {
                if (defineAt(it)) {
                    "分部长审批通过"
                } else {
                    null
                }
            }
            PartialFunction(definedAt = defineAt, f = handler)
        }()

        val president = {
            val definedAt: (ApplyEvent) -> Boolean = {
                it.amount in 500..1000
            }
            val handler: (ApplyEvent) -> String? = {
                if (definedAt(it)) {
                    "会长审批通过"
                } else {
                    null
                }
            }
            PartialFunction(definedAt = definedAt, f = handler)
        }()

        val college = {
            val definedAt: (ApplyEvent) -> Boolean = {
                it.amount > 1000
            }
            val handler: (ApplyEvent) -> String? = {
                if (definedAt(it)) {
                    "学院辅导员审批通过"
                } else {
                    null
                }
            }
            PartialFunction(definedAt = definedAt, f = handler)
        }()


        val applyChain = groupLeader orElse president orElse college
        val result = applyChain(ApplyEvent(550, "test"))
        result shouldBe "分部长审批通过"
    }

    "test demo bean" {
        val amount = 100
        val test: () -> DemoBean = {
            val isShow = {
                amount in 100..500
            }
            val mame = "test demo"
            DemoBean(isShow, mame)
        }

        val test2: Unit = {
            val isShow = {
                amount in 100..500
            }
            val mame = "test demo"
            DemoBean(isShow, mame)
            Unit
        }()



    }
})