/*
 * Copyright Bruce Liang (ldcsaa@gmail.com)
 *
 * Version	: JessMA 3.3.1
 * Author	: Bruce Liang
 * Website	: http://www.jessma.org
 * Porject	: https://code.google.com/p/portal-basic
 * Bolg		: http://www.cnblogs.com/ldcsaa
 * WeiBo	: http://weibo.com/u/1402935851
 * QQ Group	: 75375912
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jessma.dao.jdbc;

/**
 * 
 * JDBC 异常类，继承 {@link RuntimeException}
 * 
 */
@SuppressWarnings("serial")
public class JdbcException extends RuntimeException
{
	public JdbcException()
	{
		
	}
	
	public JdbcException(String desc)
	{
		super(desc);
	}
	
	public JdbcException(Throwable e)
	{
		super(e);
	}
	
	public JdbcException(String desc, Throwable e)
	{
		super(desc, e);
	}
}
