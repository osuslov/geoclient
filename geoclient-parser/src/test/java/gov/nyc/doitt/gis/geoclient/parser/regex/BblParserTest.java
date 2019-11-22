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
package gov.nyc.doitt.gis.geoclient.parser.regex;

import gov.nyc.doitt.gis.geoclient.parser.AbstractSpecTest;
import gov.nyc.doitt.gis.geoclient.parser.regex.BblParser;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BblParserTest extends AbstractSpecTest
{
	private static final Logger LOGGER = LoggerFactory.getLogger(BblParserTest.class);
	
	@Test
	public void testBblParser()
	{
		testParser(new BblParser(), LOGGER);
	}
	
	
}
