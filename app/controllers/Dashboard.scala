/*
 * Happy Melly Teller
 * Copyright (C) 2013 - 2014, Happy Melly http://www.happymelly.com
 *
 * This file is part of the Happy Melly Teller.
 *
 * Happy Melly Teller is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Happy Melly Teller is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Happy Melly Teller.  If not, see <http://www.gnu.org/licenses/>.
 *
 * If you have questions concerning this license or the applicable additional terms, you may contact
 * by email Sergey Kotlov, sergey.kotlov@happymelly.com or
 * in writing Happy Melly One, Handelsplein 37, Rotterdam, The Netherlands, 3071 PR
 */

package controllers

import play.api.mvc._
import models.{ LoginIdentity, Activity }
import securesocial.core.SecureSocial

object Dashboard extends Controller with SecureSocial {

  /**
   * About page - credits.
   */
  def about = SecuredAction { implicit request ⇒
    Ok(views.html.about(request.user.asInstanceOf[LoginIdentity]))
  }

  /**
   * API documentation page.
   */
  def api = SecuredAction { implicit request ⇒
    Ok(views.html.api.index(request.user.asInstanceOf[LoginIdentity]))
  }

  /**
   * Dashboard page - logged-in home page.
   */
  def index = SecuredAction { implicit request ⇒
    val activity = Activity.findAll
    Ok(views.html.dashboard(request.user, activity))
  }

  /**
   * Redirect to the current user’s `Person` details page. This is implemented as a redirect to avoid executing
   * the `LoginIdentity.person` database query for every page, to get the person ID for the details page URL.
   */
  def profile = SecuredAction { implicit request ⇒
    val currentUser = request.user.asInstanceOf[LoginIdentity].person
    Redirect(routes.People.details(currentUser.id.getOrElse(0)))
  }

}
