<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- Page refresh -->
            <div class="search">
              
                <a href="/YZY/admin/AdminIndexServlet"><button class="btn btn-red">管理员主页</button></a>
              
            </div>
            <!-- /Page refresh -->

            <!-- Search -->
            <div class="search" id="main-search">
              <a href="/YZY/user/UserIndexServlet"><button class="btn btn-greensea">用户主页</button></a>
            </div>
            <!-- Search end -->

            <!-- Quick Actions -->
            <ul class="nav navbar-nav quick-actions">
              
              <li class="dropdown divided">
                
                <a class="dropdown-toggle button" data-toggle="dropdown" href="#">
                  <i class="fa fa-tasks"></i>
                  <span class="label label-transparent-black">2</span>
                </a>

                <ul class="dropdown-menu wide arrow nopadding bordered">
                  <li><h1>You have <strong>2</strong> new tasks</h1></li>
                  <li>
                    <a href="#">
                      <div class="task-info">
                        <div class="desc">Layout</div>
                        <div class="percent">80%</div>
                      </div>
                      <div class="progress progress-striped progress-thin">
                        <div class="progress-bar progress-bar-green" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                          <span class="sr-only">40% Complete (success)</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="task-info">
                        <div class="desc">Schemes</div>
                        <div class="percent">15%</div>
                      </div>
                      <div class="progress progress-striped active progress-thin">
                        <div class="progress-bar progress-bar-cyan" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 15%">
                          <span class="sr-only">45% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="task-info">
                        <div class="desc">Forms</div>
                        <div class="percent">5%</div>
                      </div>
                      <div class="progress progress-striped progress-thin">
                        <div class="progress-bar progress-bar-orange" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 5%">
                          <span class="sr-only">5% Complete (warning)</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="task-info">
                        <div class="desc">JavaScript</div>
                        <div class="percent">30%</div>
                      </div>
                      <div class="progress progress-striped progress-thin">
                        <div class="progress-bar progress-bar-red" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 30%">
                          <span class="sr-only">30% Complete (danger)</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="task-info">
                        <div class="desc">Dropdowns</div>
                        <div class="percent">60%</div>
                      </div>
                      <div class="progress progress-striped progress-thin">
                        <div class="progress-bar progress-bar-amethyst" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                          <span class="sr-only">60% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <li><a href="#">Check all tasks <i class="fa fa-angle-right"></i></a></li>
                </ul>

              </li>

              <li class="dropdown divided">
                
                <a class="dropdown-toggle button" data-toggle="dropdown" href="#">
                  <i class="fa fa-envelope"></i>
                  <span class="label label-transparent-black">1</span>
                </a>

                <ul class="dropdown-menu wider arrow nopadding messages">
                  <li><h1>You have <strong>1</strong> new message</h1></li>
                  <li>
                    <a class="cyan" href="#">
                      <div class="profile-photo">
                        <img src="assets/images/ici-avatar.jpg" alt />
                      </div>
                      <div class="message-info">
                        <span class="sender">Ing. Imrich Kamarel</span>
                        <span class="time">12 mins</span>
                        <div class="message-content">Duis aute irure dolor in reprehenderit in voluptate velit esse cillum</div>
                      </div>
                    </a>
                  </li>

                  <li>
                    <a class="green" href="#">
                      <div class="profile-photo">
                        <img src="assets/images/arnold-avatar.jpg" alt />
                      </div>
                      <div class="message-info">
                        <span class="sender">Arnold Karlsberg</span>
                        <span class="time">1 hour</span>
                        <div class="message-content">Lorem ipsum dolor sit amet, consectetur adipisicing elit</div>
                      </div>
                    </a>
                  </li>

                  <li>
                    <a href="#">
                      <div class="profile-photo">
                        <img src="assets/images/profile-photo.jpg" alt />
                      </div>
                      <div class="message-info">
                        <span class="sender">John Douey</span>
                        <span class="time">3 hours</span>
                        <div class="message-content">Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia</div>
                      </div>
                    </a>
                  </li>

                  <li>
                    <a class="red" href="#">
                      <div class="profile-photo">
                        <img src="assets/images/peter-avatar.jpg" alt />
                      </div>
                      <div class="message-info">
                        <span class="sender">Peter Kay</span>
                        <span class="time">5 hours</span>
                        <div class="message-content">Ut enim ad minim veniam, quis nostrud exercitation</div>
                      </div>
                    </a>
                  </li>

                  <li>
                    <a class="orange" href="#">
                      <div class="profile-photo">
                        <img src="assets/images/george-avatar.jpg" alt />
                      </div>
                      <div class="message-info">
                        <span class="sender">George McCain</span>
                        <span class="time">6 hours</span>
                        <div class="message-content">Lorem ipsum dolor sit amet, consectetur adipisicing elit</div>
                      </div>
                    </a>
                  </li>


                  <li class="topborder"><a href="#">Check all messages <i class="fa fa-angle-right"></i></a></li>
                </ul>

              </li>

              <li class="dropdown divided">
                
                <a class="dropdown-toggle button" data-toggle="dropdown" href="#">
                  <i class="fa fa-bell"></i>
                  <span class="label label-transparent-black">3</span>
                </a>

                <ul class="dropdown-menu wide arrow nopadding bordered">
                  <li><h1>You have <strong>3</strong> new notifications</h1></li>
                  
                  <li>
                    <a href="#">
                      <span class="label label-green"><i class="fa fa-user"></i></span>
                     	在线人数
                      <span class="small">${applicationScope.OnlineCount}</span>
                    </a>
                  </li>
                  
                  <li>
                    <a href="#">
                      <span class="label label-green"><i class="fa fa-user"></i></span>
                     	登录人数
                      <span class="small">${applicationScope.LoginCount}</span>
                    </a>
                  </li>
                  
                  <li>
                    <a href="#">
                      <span class="label label-green"><i class="fa fa-user"></i></span>
                     	访问人数
                      <span class="small">${applicationScope.AccessCount}</span>
                    </a>
                  </li>

                  

                   <li><a href="#">Check all notifications <i class="fa fa-angle-right"></i></a></li>
                </ul>

              </li>

              <li class="dropdown divided user" id="current-user">
                
                <a class="dropdown-toggle options" data-toggle="dropdown" href="#">
                  ${sessionScope.Login.loginName}感谢你${sessionScope.Login.loginTime}次登录 <i class="fa fa-caret-down"></i>
                </a>
                
                <ul class="dropdown-menu arrow settings">

                  <li>
                    
                    <h3>背景颜色:</h3>
                    <ul id="color-schemes">
                      <li><a href="#" class="bg-1"></a></li>
                      <li><a href="#" class="bg-2"></a></li>
                      <li><a href="#" class="bg-3"></a></li>
                      <li><a href="#" class="bg-4"></a></li>
                      <li><a href="#" class="bg-5"></a></li>
                      <li><a href="#" class="bg-6"></a></li>
                    </ul>

                    

                  </li>

                  <li class="divider"></li>

                  <li>
                    <a href="/YZY/user/ModifyInformationServlet"><i class="fa fa-user"></i> 修改个人信息</a>
                  </li>

                  <li>
                    <a href="/YZY/user/ModifyPasswordServlet"><i class="fa fa-calendar"></i> 修改密码</a>
                  </li>

                  

                  <li class="divider"></li>

                  <li>
                    <a href="/YZY/LogoutServlet"><i class="fa fa-power-off"></i> 登出</a>
                  </li>
                </ul>
              </li>

              
            </ul>
            <!-- /Quick Actions -->
