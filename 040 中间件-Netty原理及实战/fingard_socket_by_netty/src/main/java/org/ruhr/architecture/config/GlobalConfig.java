package org.ruhr.architecture.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @apiNote
 * @author liuzq
 *
 */
@Component
@Data
public class GlobalConfig {

	@Value("${spring.application.name}")
	String applicationName;

}
