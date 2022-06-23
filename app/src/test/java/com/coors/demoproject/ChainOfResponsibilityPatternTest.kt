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
})