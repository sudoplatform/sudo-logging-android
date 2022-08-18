/*
 * Copyright © 2022 Anonyome Labs, Inc. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sudoplatform.sudologging

import android.util.Log
import java.lang.Error

class AndroidUtilsLogDriver(override var logLevel: LogLevel) : LogDriverInterface {
    override fun outputMessage(message: String) {
        Log.println(logLevel.androidLogLevel, "SudoLogging", message)
    }

    override fun outputError(error: Error, userInfo: Map<String, Any>?) {
        Log.println(Log.ERROR, "SudoLogging", "$error")
    }

    override fun processDetails(details: LogDetails): String {
        val date = details.date
        val level = details.level
        val id = details.identifier
        val f = details.file
        val function = details.function
        val message = details.message
        return "$date [${level}] [${id}] [${f}] $function > $message"
    }
}
