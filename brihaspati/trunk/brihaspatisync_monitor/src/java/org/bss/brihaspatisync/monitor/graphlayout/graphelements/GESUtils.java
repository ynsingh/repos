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

package org.bss.brihaspatisync.monitor.graphlayout.graphelements;

/**
 *  GESUtils.java
 **/


import  org.bss.brihaspatisync.monitor.graphlayout.Node;
import  org.bss.brihaspatisync.monitor.graphlayout.Edge;
import  org.bss.brihaspatisync.monitor.graphlayout.TGException;

import  java.util.*;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *    
 *  */


public class GESUtils {

   /** Returns a hashtable of Node-Integer pairs, where integers are the minimum
     * distance from the focusNode to any given Node, and the Nodes in the Hashtable
     * are all within radius distance from the focusNode.
     *
     * Nodes with edge degrees of more then maxAddEdgeCount are not added to the hashtable, and those
     * with edge degrees of more then maxExpandEdgeCount are added but not expanded.
     *
     * If unidirectional is set to true, then edges are only follewed in the forward direction.
     *
     */

    public static Hashtable calculateDistances(GraphEltSet ges, Node focusNode, int radius,
                                               int maxAddEdgeCount, int maxExpandEdgeCount,
                                               boolean unidirectional ) {
        Hashtable distHash = new Hashtable();
        distHash.put(focusNode,new Integer(0));

        TGNodeQueue nodeQ = new TGNodeQueue();
        nodeQ.push(focusNode);

        while (!nodeQ.isEmpty()) {
            Node n = nodeQ.pop();
            int currDist = ((Integer) distHash.get(n)).intValue();

            if (currDist>=radius) break;

            for (int i = 0 ; i < n.edgeCount(); i++) {
                Edge e=n.edgeAt(i);
                if(n!=n.edgeAt(i).getFrom() && unidirectional) continue;
                Node adjNode = e.getOtherEndpt(n);
                if(ges.contains(e) && !distHash.containsKey(adjNode) && adjNode.edgeCount()<=maxAddEdgeCount) {
                    if (adjNode.edgeCount()<=maxExpandEdgeCount) nodeQ.push(adjNode);
                    distHash.put(adjNode,new Integer(currDist+1));
                }
            }
        }
        return distHash;
    }

    public static Hashtable calculateDistances(GraphEltSet ges, Node focusNode, int radius ) {
        return calculateDistances(ges, focusNode, radius, 1000, 1000, false);
    }

   /** A temporary function that returns the largest connected subgraph in a GraphEltSet.
     */

//	 public static Collection getLargestConnectedSubgraph(GraphEltSet ges) {
		 public static Hashtable getLargestConnectedSubgraph(GraphEltSet ges) {
        int nodeCount = ges.nodeCount();
        if(nodeCount==0) return null;

        Vector subgraphVector = new Vector();
        for(int i=0; i<nodeCount; i++) {
            Node n = ges.nodeAt(i);
            boolean skipNode=false;
            for (int j = 0; j<subgraphVector.size();j++) {
                if(((Hashtable) subgraphVector.elementAt(j)).contains(n)) {
                    skipNode=true;
                }
            }
//					Collection subgraph = calculateDistances(ges,n,1000).keySet();
						Hashtable subgraph = calculateDistances(ges,n,1000);
            if(subgraph.size()>nodeCount/2) return subgraph; //We are done
            if (!skipNode) subgraphVector.addElement(subgraph);

        }

        int maxSize=0;
        int maxIndex=0;
        for (int j = 0; j<subgraphVector.size();j++) {
            if(((Hashtable) subgraphVector.elementAt(j)).size()>maxSize) {
                maxSize = ((Hashtable) subgraphVector.elementAt(j)).size();
                maxIndex = j;
            }
        }

        return (Hashtable) subgraphVector.elementAt(maxIndex);
    }

} // end 
