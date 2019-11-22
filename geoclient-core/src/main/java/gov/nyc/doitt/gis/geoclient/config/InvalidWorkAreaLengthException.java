/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.nyc.doitt.gis.geoclient.config;

import gov.nyc.doitt.gis.geoclient.function.WorkArea;

public class InvalidWorkAreaLengthException extends ConfigurationException
{
	private static final long serialVersionUID = 2151574091751991095L;

	public InvalidWorkAreaLengthException(WorkArea workArea, int expectedLength)
	{
		super(String.format("Length of WorkArea [id=%s] is invalid: expected=%d, actual=%d", workArea.getId(), expectedLength, workArea.length()));
	}

}
