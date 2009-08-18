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

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.IOException;

/**
 * Goal which executes WiX light to create a .msi file.
 *
 * @goal light
 * 
 * @phase package
 */
public class LightMojo
    extends AbstractMojo
{
    /**
     * Location of the WiX object files.
     * @parameter expression="${objectFiles}"
     * @required
     */
    private File[] objectFiles;
    
    /**
     * Output file
     * @parameter expression="${outputFile}"
     */
    private String outputFile;

    public void execute()
        throws MojoExecutionException
    {

        String paths = "";
        for (int x = 0; x < objectFiles.length; x++) {
          File f = objectFiles[x];
          if ( !f.exists() )
          {
         	throw new MojoExecutionException( "Object file does not exist " + objectFiles[x] );
          } else {
	        paths = paths + objectFiles[x].getAbsolutePath() + " ";
          }
        }

        try {
          String line = "light " + paths;
          
          if (outputFile != null) {
        	  line = line + " -o " + outputFile;
          }
          
          CommandLine commandLine = CommandLine.parse(line);
          DefaultExecutor executor = new DefaultExecutor();
          int exitValue = executor.execute(commandLine);
          
          if ( exitValue != 0 ) {
        	  throw new MojoExecutionException( "Problem executing light, return code " + exitValue );
          }
         
        } catch (ExecuteException e) {
          throw new MojoExecutionException( "Problem executing light", e );
        } catch (IOException e ) {
          throw new MojoExecutionException( "Problem executing light", e );
        }
    }
}
