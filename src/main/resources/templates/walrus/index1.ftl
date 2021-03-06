
<#import "_article.ftl" as article />
<#include "_navpath.ftl" />
<#import "_box.ftl" as box/>
<#import "_macro.ftl" as walrus/>
<#include "_menuMacros.ftl"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Walrus CMS ${title}</title>
	<script src="http://www.google.com/jsapi?key=ABQIAAAAxhqOC-yrr5mot7U6BtoGbRQ816ux3ivPnewnrccyFRNrgniUyhTp6G9SB4q57yFTlH55LAvsYjlitg" type="text/javascript"></script>
	<script src="/js/search.js" type="text/javascript"></script>
	<script src="/js/jquery-1.3.2.min.js"></script>
	

	<#if model.isAdmin??>
		<script src="/js/slides-admin.js"></script>
	</#if>

	<link rel="shortcut icon" href="${model.contextPath}/favicon.ico" type="image/x-icon">
	<link rel="icon" href="${model.contextPath}/favicon.ico" type="image/x-icon">
	
	<link rel="stylesheet"  href="${model.contextPath}/reset.css" type="text/css" media="screen"/>
	<link rel="stylesheet"  href="${model.contextPath}/main.css" type="text/css" media="screen"/>
	<link rel="alternate" type="application/rss+xml" title="Walrus - RSS" href="${model.contextPath}/cms/index?rss&rubricId=" />
		

	
  <!--[if IE]>
		<style type="text/css" media="screen">
      body {
        behavior: url(../csshover.htc);
      }
      #left-sidebar li {width: 100%;}
      #left-sidebar li a {height: 1%;}

      #left-sidebar ul li {
         clear:left;
         width: 156px;
      }


      #menu li a {height: 1%;}

      #menu ul li {
        float:left;
      /*  width: 50px;*/
      }
      #toggle-invisible li {float: left; width: 100%;}
      #toggle-invisible li a {height: 1%;}

      #toggle-invisible ul li {
        float:left;
        clear:left;
      }
            
		</style>
    <![endif]-->
	
	
	<!--[if IE 6]>
		<link rel="stylesheet"  href="${model.contextPath}/ie6fix.css" type="text/css" media="screen"/>
	<![endif]-->
    <!--[if IE]>
		<link rel="stylesheet"  href="${model.contextPath}/iefix.css" type="text/css" media="screen"/>    
	<![endif]-->
</head>

<body>

	
<div id="wrapper" class="clearfix">
			<a href="#content" class="hidden">pereiti prie turinio</a>
			<div id="header">
				<div class="clearfix">
					<img src="${model.contextPath}/img/logo.png" width="115" height="29" alt="Walrus logo" id="logo" />
					<#include "_topMenu.ftl"/>
				</div>	
				<div id="header-content">
					<div class="clearfix">

						<div id="short-about">

						</div>

					</div>
				</div>
			</div>

			
			<div id="main" class="clearfix">
				
				<div id="left-sidebar" class="sidebar">
					
					<div class="block green">
						<div class="rounded-corners">
							<div class="rounded-corners clearfix">
								<p>
									<a href="${model.contextPath}/cms/index?rss" class="ico rss">Get updates by RSS</a>
									<a href="mailto:info@walrus.lt" class="ico email">Write us!</a>

								</p>
								<form id="searchForm" accept-charset="utf-8" onsubmit="startSearch(this);return false;">
									<p>
									<input name="q" class="blured" id="searchInput" class="search_input"  type="text" style="vertical-align: middle;" value="<@spring.message code="walrus.search"/>" onfocus="if(this.value == '<@spring.message code="walrus.search"/>') this.value='';" onblur="if(this.value == '') this.value='<@spring.message code="walrus.search"/>'" />
									<input type="image" src="${model.contextPath}/img/search.png" style="border: none; vertical-align: middle;"/> 
									</p>
								</form>
								<span class="small" style="size: 7px; color: #aaaaaa;">Search is implemented using Google search.</span>
							</div>
						</div>

					</div>
					
					<div class="block green list">
						<div class="rounded-corners">
							<div class="rounded-corners clearfix">

							</div>
						</div>
					</div>
					
					<div class="block red list">
						<div class="rounded-corners">
							<div class="rounded-corners clearfix">

							</div>
						</div>
					</div>
				</div>

				<div id="content">
					<div id="mainContent">

					</div>
					<div id="search_column" style="display: none;">
						<span id="navpath"><a href="${model.contextPath}/cms/index"><@spring.message code="walrus.first.page"/></a> &gt; <a href="${model.contextPath}/cms/index">Paie??kos rezultatai</a></span>
						<p>&nbsp;</p>
						<h1>Paie??kos rezultatai</h1>
						<span id="searchResults">
						</span>
					</div>
					<div id="mock_gs" style="display: none;">&nbsp;</div>
				</div>
				
				<div id="right-sidebar" class="sidebar">

					<div class="block green list invisible">
						<div class="rounded-corners">
							<div class="rounded-corners clearfix">
								<p id="ultra-culture">Walrus concepts</p>

							</div>
						</div>
					</div>
					
					<div class="block green mix">
						<div class="rounded-corners">
							<div class="rounded-corners clearfix">

							</div>
						</div>
					</div>
					<div class="block commercial">


					</div>
				</div>
			</div><!--main-->			
			
			<div id="footer" class="clearfix">
				<p>Walrus was started in dark labs of <a href="http://www.megalogika.lt">MEGALOGIKA</a></p>
				<p>Now walrus is free software <a href="http://www.walrus.lt">walrus.lt</a></p>
			</div>			
			

</div> <!--wrapper -->

<script>
	//paie??kos konfig??racija
	var domainRoot = 'walrus.lt';
//	var searchFormElementId = "searchForm";
//	var searchResultsElementId = "searchResults";
//	var contentElementId = "mainContent";
	var rightElementId = "";
</script>
<script type="text/javascript" charset="utf-8">
		//<![CDATA[
			var dom = {
				hasClass: function(elem, klass) {
					return new RegExp("(^|\\s)" + klass + "(\\s|$)")
						.test(elem.className);
				},
			
				addClass: function(elem, klass) {
					if (!this.hasClass(elem, klass))
						elem.className += (elem.className ? " " : "") + klass;
					return elem;
				},
			
				removeClass: function(elem, klass) {
					elem.className = elem.className
						.replace(new RegExp("(^|\\s)" + klass + "(\\s|$)"), "$2");
					return elem;
				},
			
				toggleClass: function(elem, klass) {
					return this[(this.hasClass(elem, klass) ? 'remove' : 'add') +'Class'](elem, klass);
				}
			};

<#--			
			var invisible = document.getElementById("toggle-invisible");
			dom.addClass(invisible, "hidden");
			var toggleInvisible = function() { dom.toggleClass(invisible, "hidden"); }
-->
			function hideShowComments() {
				var comments = document.getElementById("commentList");
				if(comments) {
					dom.toggleClass(comments, "hidden");
				}
			}
			
			function toggleComments(obj, noOfComments) {
				var link = jQuery(obj);
				if (link.attr('title') && link.attr('title') == 'Hide comments') { // true == u??darin??jam komentarus
					hideShowComments();
					link.attr('title', 'Read comments');
					link.text('Comments (' + noOfComments + ')');
				} else {	// atidarin??jam komentarus
					link.attr('title', 'Hide comments');
					hideShowComments();
					link.text('Hide comments');	
				}
			}			
			
			var search = document.getElementById("searchInput");
			var searchDefaultValue = search.value;
			
			search.onfocus = function() {
				if (search.value == searchDefaultValue) {
					dom.removeClass(search, "blured");
					search.value = "";
				}
			}
			
			search.onblur = function() {
				if (search.value == "") {
					dom.addClass(search, "blured");
					search.value = searchDefaultValue;
				}
			}
		//]]>
</script>
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-1622602-8']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script></body>
</html>
