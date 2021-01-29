package com.malinskiy.marathon.config

import com.malinskiy.marathon.exceptions.ConfigurationException
import com.malinskiy.marathon.execution.Configuration
import com.malinskiy.marathon.execution.strategy.IgnoreFlakinessStrategy
import com.malinskiy.marathon.execution.strategy.ParallelShardingStrategy

class LogicalConfigurationValidator : ConfigurationValidator {
    override fun validate(configuration: Configuration) {
        when {
            configuration.flakinessStrategy !is IgnoreFlakinessStrategy &&
                    configuration.shardingStrategy !is ParallelShardingStrategy -> {
                throw ConfigurationException(
                    "Configuration is invalid: " +
                            "can't use complex sharding and complex flakiness strategy at the same time. " +
                            "See: https://github.com/Malinskiy/marathon/issues/197"
                )
            }
        }
    }
}
