package com.sudoplatform.sudologging

import java.util.*

/**
 * Basic data class holding information about a log request
 * @param identifier The identifier assigned to the `Logger` instance that invoked the output message
 * @param level The `LogLevel` type for the output
 * @param date The datetime the message was logged
 * @param message The message being logged
 * @param function The function that invoked the log
 * @param file The file the invoking `function` resides in
 * @param line The line within the invoking `function`
 */
data class LogDetails(val identifier: String,
                      val level: LogLevel,
                      val date: Date,
                      val message: String,
                      val function: String,
                      val file: String,
                      val line: Int)
