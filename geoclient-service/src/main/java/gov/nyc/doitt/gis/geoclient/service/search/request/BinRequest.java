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
package gov.nyc.doitt.gis.geoclient.service.search.request;

import gov.nyc.doitt.gis.geoclient.service.search.InputValue;

public class BinRequest extends Request
{
	private InputValue binInputValue;

	public BinRequest()
	{
		super();
	}

	public BinRequest(BinRequest anotherRequest)
	{
		super(anotherRequest.getLevel());
		this.binInputValue = anotherRequest.getBinInputValue();
	}

	public String getBin()
	{
		return stringValueOrNull(binInputValue);
	}
	
	public InputValue getBinInputValue()
	{
		return binInputValue;
	}

	public void setBinInputValue(InputValue binInputValue)
	{
		this.binInputValue = binInputValue;
	}

	@Override
	public String toString()
	{
		return "BinRequest [level= " + getLevel() + ", bin=" + getBin() + "]";
	}
	
	@Override
	public boolean containsAssignedValue()
	{
		return isAssigned(binInputValue);
	}
	
	// TODO TESTME
	@Override
	public String summarize()
	{
		return String.format("bin [%s]", getBin());
	}

}
