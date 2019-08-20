<?xml version='1.0' encoding='UTF-8' ?>
<!-- was: <?xml version="1.0"?> -->
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output encoding="UTF-8" media-type="text/html"
		method="html" indent="no" omit-xml-declaration="yes" />

	<!-- TODO customize transformation rules syntax recommendation http://www.w3.org/TR/xslt -->
	<xsl:template match="/">
		<HTML>
			<head>
				<meta http-equiv="Content-Type"
					content="text/html; charset=utf-8" />
				<title>施工中</title>

				<link
					href="http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300%7CDosis%7COswald:300,400"
					rel="stylesheet" type="text/css" />
				<link rel="stylesheet"
					href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
				<link rel="stylesheet"
					href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" />

				<link rel="stylesheet" href="/resources/underconstruction/style.css"
					type="text/css" />
				<link rel="stylesheet" href="/resources/underconstruction/hint.min.css"
					type="text/css" />

				<script type="text/javascript">
					/* SPECIFY LAUNCH DATE HERE -> year-month-day hours:minutes:seconds
					*/
					enddate = '2019-12-31 14:10:20';
				</script>
				<script src="http://code.jquery.com/jquery.js"></script>
				<script
					src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
				<link rel="stylesheet" href="/resources/underconstruction/morris.min.css" />
				<SCRIPT src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></SCRIPT>
				<script type="text/javascript"
					src="/resources/underconstruction/raphael.min.js"></script>
				<script type="text/javascript"
					src="/resources/underconstruction/morris.min.js"></script>
			</head>
			<BODY>
				<div class="header_container">
					<div class="container">
						<h3 class="header_fnt padding_top text1">🚧正在施工🚧</h3>
						<h3 class="header_fnt text2">敬請關注</h3>
					</div>
				</div>
				<div class="middle_container">
					<div class="container">
						<div class="row">
							<div class="col-md-12 center" style="height: 280px">
								<div id="donut"
									style="height: 250px; width: 250px; margin: 20px auto"></div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3"></div>
							<div class="col-md-6">
								<div class="row">
									<div class="col-md-3 center">
										<h3 id="days" class="header_fnt text1"
											style="color: #000000">8</h3>

										<h3 class="header_fnt text3">DAYS</h3>
									</div>
									<div class="col-md-3 center">
										<h3 id="hours" class="header_fnt text1"
											style="color: #000000">8</h3>

										<h3 class="header_fnt text3">HOURS</h3>
									</div>
									<div class="col-md-3 center">
										<h3 id="mins" class="header_fnt text1"
											style="color: #000000">45</h3>

										<h3 class="header_fnt text3">MINUTES</h3>
									</div>
									<div class="col-md-3 center append-6 last">
										<h3 id="secs" class="header_fnt text1"
											style="color: #000000">11</h3>

										<h3 class="header_fnt text3">SECONDS</h3>
									</div>
								</div>
							</div>
							<div class="col-md-3"></div>
						</div>
					</div>
				</div>				
				<script type="text/javascript" src="/resources/underconstruction/script.js"></script>
			</BODY>
		</HTML>
	</xsl:template>
</xsl:stylesheet>
