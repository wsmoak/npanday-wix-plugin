package npanday.plugin.wix;

/*
 * Copyright ---
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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Goal which executes WiX candle to create a .wixobj file.
 *
 * @goal candle
 * 
 * @phase package
 */
public class CandleMojo
    extends AbstractMojo
{
    /**
     * Location of the WiX source file.
     * @parameter expression="${sourceFile}"
     * @required
     */
    private File sourceFile;

    public void execute()
        throws MojoExecutionException
    {
        File f = sourceFile;

        if ( !f.exists() )
        {
        	throw new MojoExecutionException( "Source file does not exist " + sourceFile );
        }

        //TODO: execute candle
        
    }
}
