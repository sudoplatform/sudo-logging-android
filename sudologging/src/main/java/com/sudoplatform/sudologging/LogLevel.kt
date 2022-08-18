/*
 * Copyright © 2022 Anonyome Labs, Inc. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sudoplatform.sudologging

import android.util.Log

/**
 * Enumeration of supported logging levels
 */
enum class LogLevel {
    VERBOSE, DEBUG, INFO, WARNING, ERROR, NONE;

    val description: String
        get() = this.name

    val androidLogLevel: Int
        get() {
            return when (this) {
                VERBOSE -> Log.VERBOSE
                DEBUG -> Log.DEBUG
                INFO -> Log.INFO
                WARNING -> Log.WARN
                ERROR -> Log.ERROR
                NONE -> Log.DEBUG
            }
        }

    companion object {
        val allLevels = arrayOf(VERBOSE, DEBUG, INFO, WARNING, ERROR, NONE)
    }
}
