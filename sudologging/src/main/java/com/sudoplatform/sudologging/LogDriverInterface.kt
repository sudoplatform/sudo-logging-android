/*
 * Copyright © 2022 Anonyome Labs, Inc. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sudoplatform.sudologging

import java.lang.Error

interface LogDriverInterface {

    /**
     * The maximum `LogLevel` type the driver can process.
     * Any logs above this level will not be logged. VERBOSE > NONE
     */
    var logLevel: LogLevel

    /**
     * Will process the given `LogDetails` instance and output a formatted message for logging
     * @param details The details to process
     */
    fun processDetails(details: LogDetails): String

    /**
     * A default implementation of the processDetails method which creates a readable string for logging
     * @param details The details to process
     */
    fun processDetailsDefault(details: LogDetails): String {
        val date = details.date
        val level = details.level
        val id = details.identifier
        val f = details.file
        val line = details.line
        val function = details.function
        val message = details.message
        return "$date [${level}] [${id}] [${f}] $line $function > $message"
    }

    /**
     * Will log the given message to whatever mechanism the driver instance supports
     * @param message The message to log
     */
    fun outputMessage(message: String)

    /**
     * Will process the given error in whatever way the driver instance supports
     * @param error: The error to process
     * @param userInfo: Optional dictionary of error keys and values that provide additional error information
     */
    fun outputError(error: Error, userInfo: Map<String, Any>?)
}
