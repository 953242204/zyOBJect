<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/" mode="stories">
		<xsl:apply-templates select="." mode="story" />
			
<!-- 		        <xsl:for-each select="ancestor-or-self::*"> -->
<!-- 		            <xsl:value-of select="concat('/',local-name())"/> -->
<!-- 		        </xsl:for-each> -->
<!-- 		        <xsl:text>&#xA;</xsl:text> -->
<!-- 		        <xsl:apply-templates select="node()"/> -->
		   
	</xsl:template>

	<!--story-->
	<xsl:template match="/" mode="story">
		<DIV class="story_name">
			<DIV class="card mb-4">
				<!-- carousel -->
				<DIV class="carousel slide" data-ride="carousel">
					<xsl:attribute name="id">
						<xsl:value-of select="generate-id(@id)"/>
					</xsl:attribute>
					<!-- story-title -->
					<DIV class="p-2 mb-5">
						<BUTTON type="button" class="float-left btn btn-outline-light">
							<A title="homepage" class="text-dark" onclick="getHomepage({author/@id})">
								<xsl:choose>
									<xsl:when test="author/@profileImgUrl">
										<img src="{author/@profileImgUrl}" class="rounded-circle img-personal-headPortrait"/>
									</xsl:when>
									<xsl:otherwise>
										<img src="resources/1.jpg" class="rounded-circle img-personal-headPortrait"/>
									</xsl:otherwise>
								</xsl:choose>
								<xsl:value-of select="author/@nickname"/>
							</A>
						</BUTTON>
						<DIV class="float-right">
							<A CLass="text-muted">
								<xsl:value-of select="@postedAt"/>
							</A>
						</DIV>
					</DIV>
					<OL class="carousel-indicators">
						<xsl:for-each select = "storyImages/storyImage">
							<LI data-slide-to="{@count}" class="active">
								<xsl:attribute name="Data-target">&#35;
									<xsl:value-of select="generate-id(@id)"/>
								</xsl:attribute>
								<xsl:if test="@count=0">
									<xsl:attribute name="class">active</xsl:attribute>
								</xsl:if>
							</LI>
						</xsl:for-each>
					</OL>					
					<DIV class="carousel-inner">
						<xsl:apply-templates select="." mode="storyImage"/>
					</DIV>
					<A CLass="carousel-control-prev" href="#{generate-id(@id)}" role="button" data-slide="prev">
						<SPAN class="carousel-control-prev-icon" aria-hidden="true"></SPAN>
						<SPAN class="sr-only">Previous</SPAN>
					</A>
					<A CLass="carousel-control-next" href="#{generate-id(@id)}" role="button" data-slide="next">
						<SPAN class="carousel-control-next-icon" aria-hidden="true"></SPAN>
						<SPAN class="sr-only">Next</SPAN>
					</A>
				</DIV>
			</DIV>
			<DIV class="card-body">
				<P CLass="card-text">
					<xsl:value-of select="content"/>
				</P>
				<DIV>
					<DIV class="d-flex justify-content-between align-items-center">
						<DIV class="btn-group">
							<FORM>
								<BUTTON type="button" class="btn btn-lg btn-light">
									<I CLass="fas fa-share-alt"/>
								</BUTTON>
							</FORM>
							<FORM>
								<BUTTON data-target = "&#35;&#97;{generate-id(@id)}" type="button" class="btn btn-lg btn-light" data-toggle="collapse" aria-controls="collapseExample" aria-expanded="false">
									<I CLass="fas fa-comments"/>
								</BUTTON>
							</FORM>
							<FORM action="/like/" method="POST" class="mode">
								<INPUT name="story" type="hidden" value="{@id}"/>
								<INPUT name="who" type="hidden" value="{/document/@me}"/>
								<BUTTON type="submit" class="btn btn-lg btn-light">
									<I title="{@emotions}" CLass="far fa-heart"/>
								</BUTTON>
								<!--<I CLass="{like/emotion[selected]}"/>-->
							</FORM>
							<FORM action="/bookmark/" method="POST" class="mode">
								<INPUT name="story" type="hidden" value="{@id}"/>
								<INPUT name="who" type="hidden" value="{/document/@me}"/>
								<BUTTON type="submit" class="btn btn-lg btn-light">
									<I CLass="far fa-bookmark"></I>
								</BUTTON>
							</FORM>
						</DIV>
					</DIV>
					<DIV class="col collapse" id="&#97;{generate-id(@id)}">
						<HR/>
						<FORM action="/postComment/" method="PUT" class="mode" style="margin-bottom:-10px">
							<P CLass="lead emoji-picker-container">
								<INPUT type="hidden" name="who" value="{/document/@personnelHref}"/>
								<INPUT type="hidden" name="storyId" value="{@id}"/>
								<INPUT type="hidden" name="storyHref" value="{href}"/>
								<INPUT type="text" name="content" placeholder="留言..."/>
								<BUTTON type="submit" class="btn btn-lg btn-light">送出</BUTTON>
							</P>
						</FORM>
						<BR/>
						<BR/>
						<DIV class="col" id="{../@id}">
							<xsl:apply-templates select="comment"/>
						</DIV>
						<BUTTON class="topic-more-comm">載入更多留言</BUTTON>
					</DIV>
				</DIV>
			</DIV>
		</DIV>
	</xsl:template>
		
	<!--url-->
	<xsl:template match="/" mode="storyImage">
		<DIV class="{@mode}">
			<IMG class="d-block w-100" alt="First slide"
			src="https://www.orangelady99.com/tw/upload_files/fonlego-rwd/prodpic/D_%E8%8F%93%E7%84%B6%E5%B9%B8%E7%A6%8F-%E5%A4%9A%E9%81%94%E5%8D%81%E5%B9%BE%E7%A8%AE%E7%86%B1%E8%B3%A3%E5%A4%A9%E7%84%B6%E6%B0%B4%E6%9E%9C%E4%B9%BE.jpg">
<!-- 				<xsl:attribute name="src"> -->
<!-- 					<xsl:value-of select="url"/> -->
<!-- 				</xsl:attribute> -->
			</IMG>
			 
		</DIV>
	</xsl:template>
	
	<!--comment-->
	<xsl:template match="/" mode="comment">
		<UL class="topic-comm" style="clear:both">
			<LI>
				<DIV class="d-inline">
					<A CLass="text-left text-dark" title="{who/@nickname}" href="{who/@nickname}">
						<xsl:value-of select="who/@nickname"/> 
					</A>
				</DIV>
				<SPAN class="d-inline">
					<xsl:value-of select="content"/>
					<!--<A HRef="/shareefoneal/">@shareefoneal</A>-->
				</SPAN>
			</LI>
		</UL>
	</xsl:template>

</xsl:stylesheet>