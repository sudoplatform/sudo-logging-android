/*
 * Copyright © 2022 Anonyome Labs, Inc. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sudoplatform.sudologging

/**
 * `Logging` conforming instances provide a convenience method to generate a `Logger` instance
 */
interface Logging {

    /**
     * Will generate a new `Logger` instance with the given identifier
     * @param logIdentifier Identifier (preferably unique) used in the output message when logging
     */
    fun createLogger(logIdentifier: String): Logger
}
