package com.dabao.meun.entity;


/**
 *Created by dabao 2016Äê6ÔÂ26ÈÕ
 */
public class JokeUrl {

		private int allpages;
		private int ret_code;
		private Contentlist contentlist;

		public JokeUrl(int allpages, int ret_code, Contentlist contentlist) {
			super();
			this.allpages = allpages;
			this.ret_code = ret_code;
			this.contentlist = contentlist;
		}

		public int getAllpages() {
			return allpages;
		}

		public void setAllpages(int allpages) {
			this.allpages = allpages;
		}

		public int getRet_code() {
			return ret_code;
		}

		public void setRet_code(int ret_code) {
			this.ret_code = ret_code;
		}

		public Contentlist getContentlist() {
			return contentlist;
		}

		public void setContentlist(Contentlist contentlist) {
			this.contentlist = contentlist;
		}

		@Override
		public String toString() {
			return "JokeUrl [allpages=" + allpages + ", ret_code=" + ret_code
					+ ", contentlist=" + contentlist + "]";
		}

	}
