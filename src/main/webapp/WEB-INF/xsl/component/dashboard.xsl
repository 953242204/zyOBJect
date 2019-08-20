<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/" mode="dashboard">
		<DIV class="card mb-3">
			<INPUT name="whoId" type="hidden" value="{@id}" />
			<TEXTAREA class="form-control textarea-control" rows="2"
				placeholder="請輸入文字...." data-emojiable="true"
				data-emoji-input="unicode">
			</TEXTAREA>
			<DIV class="p-1">
				<DIV class="file-loading">
					<INPUT id="input-b9" name="input-b9[]" multiple=""
						type="file" />
				</DIV>
				<DIV id="kartik-file-errors"></DIV>
			</DIV>

			<!--tag friend dashboad -->
			<DIV class="modal fade" id="tagFriend" tabindex="-1"
				role="dialog" aria-labelledby="tagFriendLabel" aria-hidden="true">
				<DIV class="modal-dialog modal-lg" role="document">
					<DIV class="modal-content">
						<DIV class="modal-header">
							<H5 class="modal-title" id="tagFriendLabel">標註朋友</H5>
							<BUTTON type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<SPAN aria-hidden="true">×</SPAN>
							</BUTTON>
						</DIV>
						<DIV class="modal-body">
							<DIV class="input-group" style="width: 100%;">
								<INPUT type="text" class="form-control"
									id="modalTest_input" autocomplete="off"
									style="border-radius: 4px; background: rgb(255, 255, 255);"
									data-id="" alt="" />
								<DIV class="input-group-btn">
									<BUTTON type="button"
										class="btn btn-default dropdown-toggle" data-toggle=""
										style="display: none;">
										<SPAN class="caret"></SPAN>
									</BUTTON>
									<UL class="dropdown-menu dropdown-menu-right" role="menu"
										style=" max-height: 375px; overflow: auto; display: block;">
										<TABLE class="table table-condensed table-sm"
											style="margin:0">
											<THEAD>
												<TR id="followerCount">

												</TR>
											</THEAD>
											<TBODY id="followers">


											</TBODY>
										</TABLE>
									</UL>
								</DIV>
								<!--/btn-group -->
							</DIV>
						</DIV>
						<DIV class="div-button">
							<BUTTON type="button" class="btn btn-secondary"
								data-dismiss="modal" onclick="addInputBox()">确定</BUTTON>
							<BUTTON type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</BUTTON>
						</DIV>
					</DIV>
				</DIV>
			</DIV>    <!--TAG FRIEND DASHBOAD END -->
			<!--file upload dashboad -->

		</DIV>

	</xsl:template>

</xsl:stylesheet>