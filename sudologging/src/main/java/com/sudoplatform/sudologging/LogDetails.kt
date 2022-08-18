/*
 * Copyright © 2022 Anonyome Labs, Inc. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sudoplatform.sudologging

import java.util.Date

/**
 * Basic data class holding information about a log request
 *
 * @property identifier [String] The identifier assigned to the `Logger` instance that invoked the output message.
 * @property level [LogLevel] The `LogLevel` type for the output.
 * @property date [Date] The datetime the message was logged.
 * @property message [String] The message being logged.
 * @property function [String] The function that invoked the log.
 * @property file [String] The file the invoking `function` resides in.
 * @property line [Int] The line within the invoking `function`.
 */
data class LogDetails(val identifier: String,
                      val level: LogLevel,
                      val date: Date,
                      val message: String,
                      val function: String,
                      val file: String,
                      val line: Int)
