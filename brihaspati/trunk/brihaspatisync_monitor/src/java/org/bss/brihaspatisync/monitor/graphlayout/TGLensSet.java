/* Original source which is modified resulting in this file, is copyrighted (c)
 * 2001-2002 by Alexander Shapiro in made available under the license as
 * reproduced below.
 *
 * TouchGraph LLC. Apache-Style Software License
 *
 *
 * Copyright (c) 2001-2002 Alexander Shapiro. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by
 *        TouchGraph LLC (http://www.touchgraph.com/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "TouchGraph" or "TouchGraph LLC" must not be used to endorse
 *    or promote products derived from this software without prior written
 *    permission.  For written permission, please contact
 *    alex@touchgraph.com
 *
 * 5. Products derived from this software may not be called "TouchGraph",
 *    nor may "TouchGraph" appear in their name, without prior written
 *    permission of alex@touchgraph.com.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL TOUCHGRAPH OR ITS CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 * 
 * The changes done in the original source to suit our requirement are released
 * under the license as given in LICENSE file distributed with this source.
 * All such changes are Copyrighted (c) 2011-2012 ETRG, IIT Kanpur.
 */

package org.bss.brihaspatisync.monitor.graphlayout;

/**
 * TGLensSet.java
 */

import  java.util.Vector;

/**
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 */

public class TGLensSet {

    Vector lenses=new Vector();

    public void addLens( TGAbstractLens l ) {
        lenses.addElement(l);
    }

    public void applyLens( TGPoint2D p ) {
        if (lenses.isEmpty()) return;
        //else
        for (int i=0; i<lenses.size(); i++) {
            ((TGAbstractLens)lenses.elementAt(i)).applyLens(p);
        }
    }

    public void undoLens( TGPoint2D p ) {
        if (lenses.isEmpty()) return;
        //else
        for (int i=lenses.size()-1; i>=0; i--) {
            ((TGAbstractLens) lenses.elementAt(i)).undoLens(p);
        }
    }

    /** Convert draw position to real position. */
    public TGPoint2D convRealToDraw( TGPoint2D p ) {
        TGPoint2D newp = new TGPoint2D(p);
        applyLens(newp);
        return newp;
    }

    /** Convert draw position to real position. */
    public TGPoint2D convRealToDraw( double x, double y ) {
        TGPoint2D newp = new TGPoint2D(x,y);
        applyLens(newp);
        return newp;
    }

    /** Convert real position to draw position. */
    public TGPoint2D convDrawToReal( TGPoint2D p ) {
        TGPoint2D newp = new TGPoint2D(p);
        undoLens(newp);
        return newp;
    }

    /** Convert real position to draw position. */
    public TGPoint2D convDrawToReal(double x, double y) {
        TGPoint2D newp = new TGPoint2D(x, y);
        undoLens(newp);
        return newp;
    }

} // end 





