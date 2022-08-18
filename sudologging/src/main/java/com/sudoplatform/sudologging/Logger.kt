/*
 * Copyright © 2022 Anonyome Labs, Inc. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sudoplatform.sudologging

import java.util.Date

class Logger(private val identifier: String, private val driver: LogDriverInterface) {

    /**
     * Checksum's for each file are generated and are used to create a checksum that is used when
     * publishing to maven central. In order to retry a failed publish without needing to change any
     * functionality, we need a way to generate a different checksum for the source code. We can
     * change the value of this property which will generate a different checksum for publishing
     * and allow us to retry. The value of `version` doesn't need to be kept up-to-date with the
     * version of the code.
     */
    private val version: String = "1.3.1"

    private fun log(logLevel: LogLevel, closure: () -> String?) {
        if (logLevel < driver.logLevel) { return }

        // go to the fourth item in the stack trace to get the line that issued the log request
        // item 0 and 1 relate to the stacktrace itself
        // 2 is the line requesting the stackTrace (eg. the line below assigning a value to `function`)
        // 3 is the line calling this method, and 4 is the line that calls the public log method
        val stackTraceItem = Thread.currentThread().stackTrace[4]

        val function: String = stackTraceItem.methodName
        val file: String = stackTraceItem.fileName ?: "Unknown"
        val line: Int = stackTraceItem.lineNumber

        val message = closure() ?: return

        val details = LogDetails(identifier, logLevel, Date(), message, function, file, line)

        driver.outputMessage(driver.processDetailsDefault(details))
    }

    /**
     * Will log the result of the given message closure with the underlying driver using the `LogLevel.VERBOSE` level
     * @param message The message to log
     */
    fun verbose(message: () -> String?) {
        log(LogLevel.VERBOSE, message)
    }

    /**
     * Will log the given message with the underlying driver using the `LogLevel.VERBOSE` level
     * @param message The message to log
     */
    fun verbose(message: String?) {
        log(LogLevel.VERBOSE, { message })
    }

    /**
     * Will log the result of the given message closure with the underlying driver using the `LogLevel.DEBUG` level
     * @param message The message to log
     */
    fun debug(message: () -> String?) {
        log(LogLevel.DEBUG, message)
    }

    /**
     * Will log the given message with the underlying driver using the `LogLevel.DEBUG` level
     * @param message The message to log
     */
    fun debug(message: String?) {
        log(LogLevel.DEBUG, { message })
    }

    /**
     * Will log the result of the given message closure with the underlying driver using the `LogLevel.INFO` level
     * @param message The message to log
     */
    fun info(message: () -> String?) {
        log(LogLevel.INFO, message)
    }

    /**
     * Will log the given message with the underlying driver using the `LogLevel.INFO` level
     * @param message The message to log
     */
    fun info(message: String?) {
        log(LogLevel.INFO, { message })
    }

    /**
     * Will log the result of the given message closure with the underlying driver using the `LogLevel.WARNING` level
     * @param message The message to log
     */
    fun warning(message: () -> String?) {
        log(LogLevel.WARNING, message)
    }

    /**
     * Will log the given message with the underlying driver using the `LogLevel.WARNING` level
     * @param message The message to log
     */
    fun warning(message: String?) {
        log(LogLevel.WARNING, { message })
    }

    /**
     * Will log the result of the given message closure with the underlying driver using the `LogLevel.ERROR` level
     * @param message The message to log
     */
    fun error(message: () -> String?) {
        log(LogLevel.ERROR, message)
    }

    /**
     * Will log the given message with the underlying driver using the `LogLevel.ERROR` level
     * @param message The message to log
     */
    fun error(message: String?) {
        log(LogLevel.ERROR, { message })
    }

    /**
     * Will output, but not log, the given error instance
     * @param error The error to send
     * @param userInfo Optional dictionary of error keys and values to send with the error
     */
    fun outputError(error: Error, userInfo: Map<String, Any>? = null) {
        driver.outputError(error, userInfo)
    }
}
