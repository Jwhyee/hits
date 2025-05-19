package com.example.hits.util

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext

object AutowireHelper {
    fun autoWireMockMvc(vararg controllers: Any): MockMvc {
        val context = AnnotationConfigWebApplicationContext().apply {
            register(*controllers.map { it::class.java }.toTypedArray())
            refresh()
        }

        return MockMvcBuilders.webAppContextSetup(context).build()
    }
}