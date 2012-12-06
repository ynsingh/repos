<%-- 
    Document   : LikeDislikeCounter
    Created on : Aug 8, 2012, 4:05:50 PM
    Author     : Vinay Kr. Sharma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sd" uri="/struts-dojo-tags" %>
<%@taglib  prefix="sj" uri="/struts-jquery-tags" %>
<sj:head/>

<s:if test="likelist!='null'">
    <s:iterator value="likedislist">
        <s:if test="Ldval=='Like'">
            <s:url id="Unlike" value="UndoLike"><s:param name="evidenceSubId" value="evidenceSubId"/><s:param name="likeDislikeId" value="likeDislikeId"/></s:url>
            <s:url id="Dislike" value="SaveDislike"><s:param name="evidenceSubId" value="evidenceSubId"/><s:param name="likeDislikeId" value="likeDislikeId"/><s:param name="L" value="0"/><s:param name="D" value="1"/><s:param name="commentId" value="commentId"/></s:url>
            <td><sd:a href="%{Unlike}">Unlike</sd:a></td> 
            <td><sd:a href="%{Dislike}">Dislike</sd:a></td>
            <td>
                Likes :&nbsp;<s:property value="likeCount"/>
            </td>&nbsp;
            <td>
                Dislikes :&nbsp;<s:property value="dislikeCount"/>
            </td>

        </s:if>
        <s:elseif test="Ldval=='DisLike'">
            <s:url id="Undo" value="UndoDislike"><s:param name="evidenceSubId" value="evidenceSubId"/><s:param name="likeDislikeId" value="likeDislikeId"/></s:url>
            <s:url id="Like" value="SaveLike"><s:param name="evidenceSubId" value="evidenceSubId"/><s:param name="likeDislikeId" value="likeDislikeId"/><s:param name="L" value="1"/><s:param name="D" value="0"/><s:param name="commentId" value="commentId"/></s:url>
            <td> <sd:a href="%{Like}">Like</sd:a></td>
            <td><sd:a href="%{Undo}">Undo</sd:a></td>
            <td>Likes :&nbsp;<s:property value="likeCount"/> </td>&nbsp;<td>Dislikes :&nbsp;<s:property value="dislikeCount"/> </td>
        </s:elseif>
        <s:elseif test="Ldval=='null'">
            <s:url id="Like" value="SaveLike"><s:param name="evidenceSubId" value="evidenceSubId"/><s:param name="likeDislikeId" value="likeDislikeId"/><s:param name="L" value="1"/><s:param name="D" value="0"/><s:param name="commentId" value="commentId"/></s:url>
            <td> <sd:a href="%{Like}">Like</sd:a></td> 
            <s:url id="Dislike" value="SaveDislike"><s:param name="evidenceSubId" value="evidenceSubId"/><s:param name="likeDislikeId" value="likeDislikeId"/><s:param name="L" value="0"/><s:param name="D" value="1"/><s:param name="commentId" value="commentId"/></s:url>
            <td> <sd:a href="%{Dislike}">Dislike</sd:a></td>
            <td>Likes :&nbsp;<s:property value="likeCount"/> </td>&nbsp;<td>Dislikes :&nbsp;<s:property value="dislikeCount"/> </td>
        </s:elseif>

    </s:iterator>
</s:if>
<s:elseif test="likelist=='null'">
    <s:url id="Like" value="SaveLike"><s:param name="evidenceSubId" value="evidenceSubId"/><s:param name="likeDislikeId" value="likeDislikeId"/><s:param name="L" value="1"/><s:param name="D" value="0"/><s:param name="commentId" value="commentId"/></s:url>
    <td> <sd:a href="%{Like}">Like</sd:a></td> 
    <s:url id="Dislike" value="SaveDislike"><s:param name="evidenceSubId" value="evidenceSubId"/><s:param name="likeDislikeId" value="likeDislikeId"/><s:param name="L" value="0"/><s:param name="D" value="1"/><s:param name="commentId" value="commentId"/></s:url>
    <td><sd:a href="%{Dislike}">Dislike</sd:a></td>
    <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
    <td>Likes :&nbsp;<s:property value="likeCount"/> </td>&nbsp;<td>Dislikes :&nbsp;<s:property value="dislikeCount"/> </td>
</s:elseif>

