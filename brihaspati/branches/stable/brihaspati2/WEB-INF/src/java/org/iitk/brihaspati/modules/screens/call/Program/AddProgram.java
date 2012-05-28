package org.iitk.brihaspati.modules.screens.call.Program;

/*
 * @(#)AddProgram.java
 *
 *  Copyright (c) 2010 ETRG,IIT Kanpur.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of ETRG, I.I.T. Kanpur
 *
 */

/**
 *@author: <a href="mailto:richa.tandon1@gmail.com">Richa Tandon</a>
 */

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.iitk.brihaspati.modules.screens.call.SecureScreen;
import org.iitk.brihaspati.modules.utils.ErrorDumpUtil;
import org.apache.turbine.util.parser.ParameterParser;

public class AddProgram extends SecureScreen
{
        /**
         * Place all the data object in the context for use in the template.
         * @param data RunData instance
         * @param context Context instance
         */

        public void doBuildTemplate(RunData data, Context context)
        {
                ParameterParser pp = data.getParameters();
                String tdcolor=pp.getString("count","1");
                context.put("tdcolor",tdcolor);
                String prgcode=pp.getString("pcode","");
                context.put("pgcode",prgcode);
                String prgname=pp.getString("pname","");
                context.put("pgname",prgname);
                String alsprgcode=pp.getString("alspcode","");
                context.put("alspgcode",alsprgcode);
                String alsprgname=pp.getString("alspname","");
                context.put("alspgname",alsprgname);
                String dscrp=pp.getString("descrp","");
                context.put("descrp",dscrp);
                String status=pp.getString("status","");
                context.put("status",status);

        }
}

