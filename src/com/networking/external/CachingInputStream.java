/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.networking.external;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CachingInputStream extends InputStream
{
    private static final int EOF = -1;   // end of file
    
    private final InputStream is;
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public CachingInputStream(InputStream is)
    {
        this.is = is;
    }

    public byte[] getCache()
    {
        return baos.toByteArray();
    }

    public int read() throws IOException
    {
        int result = is.read();
        
        if (result != EOF) {
            baos.write(result);
        }
        
        return result;
    }

    public int available() throws IOException
    {
        return is.available();
    }

    public void close() throws IOException
    {
        is.close();
    }

}
