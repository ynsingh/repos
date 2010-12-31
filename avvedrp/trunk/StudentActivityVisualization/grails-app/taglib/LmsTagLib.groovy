/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahis
 */
class LmsTagLib {

    def SideMenu = {attrs ->
		  out << g.render(template: '/sideMenu')
         }

    def StyleSwitcher = {attrs ->
		  out << g.render(template: '/styleSwitcher')
         }
}

