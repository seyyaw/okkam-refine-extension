/*

Copyright 2010, Google Inc.
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

 * Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above
copyright notice, this list of conditions and the following disclaimer
in the documentation and/or other materials provided with the
distribution.
 * Neither the name of Google Inc. nor the names of its
contributors may be used to endorse or promote products derived from
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,           
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY           
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 */

package com.google.refine.ENSUploader.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONWriter;
import org.openrdf.rio.RDFFormat;

import com.google.refine.ProjectManager;
import com.google.refine.browsing.Engine;
import com.google.refine.commands.Command;
import com.google.refine.model.Project;
import com.google.refine.rdf.app.ApplicationContext;
import com.google.refine.ENSUploader.rdf.uploaders.RdfUploader;

public class UploadAsRdfCommand extends Command {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProjectManager.singleton.setBusy(true);
        try {
            Project project = getProject(request);
            Engine engine = getEngine(request, project);

            RdfUploader uploader=new RdfUploader(new ApplicationContext(), RDFFormat.TURTLE);
                uploader.upload(project, engine); 
                
                JSONWriter writer = new JSONWriter(response.getWriter());
                writer.object();
                writer.key("key");
                writer.value(uploader.countSubjs);
                writer.endObject();
             
//                //Upload Information Page
//                PrintWriter out = response.getWriter(); 
//                response.setCharacterEncoding("UTF-8");
//                response.setHeader("Content-Type", "text/html");
//                out = response.getWriter();
//                out.println("<html><head><title>Upload Information</title></head><body>");
//                out.println("<p>"+uploader.countSubjs+" entities are created succesfuly</p>");
//                out.println("<form method=\"post\"><input type=\"button\" value=\"Close Window\"" +
//                		"onclick=\"window.close()\"></form>");
//                out.println("</body></html>");
                
           
        } catch (Exception e) {
            // Use generic error handling rather than our JSON handling
            throw new ServletException(e);
        } finally {
            ProjectManager.singleton.setBusy(false);
        }
    }
}
