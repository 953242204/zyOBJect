<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<HEAD>
				<!-- Required meta tags -->
				<META charset="utf-8" />
				<META name="viewport"
					content="width=device-width, initial-scale=1, shrink-to-fit=no" />
				<TITLE>Hello, 這裡是BBMall!</TITLE>
			</HEAD>
			<body>
				<xsl:apply-templates select="." mode="header" />
				<MAIN class="container">
					<DIV class="my-3 p-3 rounded">
						<DIV class="row">
							<!-- sidebar -->
							<xsl:apply-templates select="."	mode="sideBar" />
							<DIV class="col-md-8 col-sm-12">
								<xsl:apply-templates select="."	mode="dashboard" />
								<xsl:apply-templates select="."	mode="stories" />
							</DIV>
						</DIV>
					</DIV>
					<HR />
					<DIV class="text-center">
						載入中...
						<DIV class="spinner-grow" role="status">
							<SPAN class="sr-only"></SPAN>
						</DIV>
					</DIV>
					<HR />

					<FOOTER>
						<DIV class="footer-copyright text-center py-3 d-xl-none">
							© 2019 Copyright:
							<A HRef="https://mdbootstrap.com/education/bootstrap/"> redan.com</A>
						</DIV>
					</FOOTER>

					<FOOTER class="page-footer fixed-bottom bg-dark">
						<DIV class="row justify-content-around p-2 d-xl-none">
							<DIV class="col-2 text-white mobileFooterButton">
								<I CLass="fas fa-home"></I>
							</DIV>
							<DIV class="col-2 text-white mobileFooterButton">
								<I CLass="fas fa-shopping-bag"></I>
							</DIV>
							<DIV class="col-2 text-white mobileFooterButtonPlus">
								<I CLass="far fa-plus-square"></I>
							</DIV>
							<DIV class="col-2 text-white mobileFooterButton">
								<I CLass="fas fa-bookmark"></I>
							</DIV>
							<DIV class="col-2 text-white mobileFooterButton">
								<I CLass="fas fa-user"></I>
							</DIV>
						</DIV>
					</FOOTER>
				</MAIN>
				
				<xsl:apply-templates select="." mode="footer" />

				<!-- Bootstrap CSS -->
				<LINK rel="stylesheet"
					href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
					integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
					crossorigin="anonymous" />
				<LINK rel="stylesheet"
					href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
					crossorigin="anonymous" />
				<LINK href="/resources/css/fileinput.css" media="all"
					rel="stylesheet" type="text/css" />
				<LINK rel="stylesheet" type="text/css"
					href="https://bootstrap.hexschool.com/docs/4.1/examples/offcanvas/offcanvas.css" />
				<LINK rel="stylesheet" href="/resources/css/style6.css" />
				<LINK href="/resources/emoji/css/emoji.css" rel="stylesheet" />
				<LINK rel="stylesheet"
					href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" />
				<LINK rel="stylesheet" type="text/css"
					href="/resources/css/redan.css" />

				<!--<SCRIPT src="https://code.jquery.com/jquery-3.3.1.min.js" crossorigin="anonymous"></SCRIPT> -->
				<SCRIPT src="/resources/js/jquery-3.3.1.min.js"></SCRIPT>
				<SCRIPT
					src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
					integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
					crossorigin="anonymous">
				</SCRIPT>
				<SCRIPT src="/resources/js/bootstrap-suggest.js"></SCRIPT>
				<SCRIPT src="/resources/js/plugins/sortable.js"></SCRIPT>
				<SCRIPT src="/resources/js/fileinput.js"></SCRIPT>
				<SCRIPT src="/resources/js/locales/fr.js"></SCRIPT>
				<SCRIPT src="/resources/js/locales/es.js"></SCRIPT>
				<SCRIPT src="/resources/themes/fas/theme.js"></SCRIPT>
				<SCRIPT src="/resources/themes/explorer-fas/theme.js"></SCRIPT>
				<SCRIPT
					src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
					integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
					crossorigin="anonymous">
				</SCRIPT>
				<!-- Begin emoji-picker JavaScript -->
				<SCRIPT src="/resources/emoji/js/config.js"></SCRIPT>
				<SCRIPT src="/resources/emoji/js/util.js"></SCRIPT>
				<SCRIPT src="/resources/emoji/js/jquery.emojiarea.js"></SCRIPT>
				<SCRIPT src="/resources/emoji/js/emoji-picker.js"></SCRIPT>
				<SCRIPT src="/resources/js/aws-sdk-2.283.1.min.js"></SCRIPT>
				<SCRIPT src="/resources/js/s3init.js"></SCRIPT>
				<SCRIPT src="/resources/js/friend.js"></SCRIPT>
				<!-- End emoji-picker JavaScript -->

				<SCRIPT src="/resources/js/init.js"></SCRIPT>
				<!-- Begin 社群相關功能 -->
				<SCRIPT src="/resources/js/likeAndBookSubmit.js"></SCRIPT>
				<SCRIPT src="/resources/js/postStory.js"></SCRIPT>
				<SCRIPT src="/resources/js/postComment.js"></SCRIPT>


			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>