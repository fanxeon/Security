package crypto.students;

import java.math.BigInteger;

import org.apache.log4j.Logger;
import org.junit.Test;

import crypto.client.Main;
import crypto.students.StreamCipher;
import crypto.students.Supplementary;

public class TestSupplementary {
	private static Logger log = Logger.getLogger(TestSupplementary.class);
	@Test
	public void testStreamCipher() {
		String big = "415897726212538720767584623938377542218843650885786488543557849920563944820657401556147072220807050423844611527817088743264179887246035449031879964033048917437051768727745163996083995699396309860629605332639267450328379289961205789359923142431676348109877819086396004913235006262427231898532203764657706261780748597526471127787542155628754978941021278802504747939847153450812209928520258539639347968927907337576400038705453704498741239631581573919339705649004208586949422810873621196157474272177586468236634536851618181572350294408509526514361027546939234421045026063811415872877733865949984217287267027217419";
		System.out.println("Big Number: " + big);
		BigInteger checksum = Supplementary.parityWordChecksum(new BigInteger(big));
		System.out.println("parity checksum: " + checksum);
		
		
		BigInteger share = new BigInteger("4");
		BigInteger prime = new BigInteger("11");
		BigInteger p = new BigInteger("3");
		BigInteger q = new BigInteger("5");
		StreamCipher cipher = new StreamCipher(share, prime, p, q);
		
		BigInteger val = new BigInteger("13892949480140891204");
		System.out.println("msb: " + new BigInteger(1, new byte[]{cipher.msb(val, 8)}));
		
		String msg = "{\"created_at\":\"Thu May 14 14:37:57 +0000 2015\",\"id\":598859802400268289,\"id_str\":\"598859802400268289\",\"text\":\"Good morning! \u2665\",\"source\":\"\u003ca href=\"http://twitter.com/download/android\" rel=\"nofollow\"\u003eTwitter for Android\u003c/a\u003e\",\"truncated\":false,\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":73727137,\"id_str\":\"73727137\",\"name\":\"Amber Bruns\u2702\",\"screen_name\":\"ColourQueenAB\",\"location\":\"SAN ANTONIO, TEXAS\",\"url\":null,\"description\":\"COLOUR Queen B. #Hair Stylist in the Making. I LOVE COLOR! By Any Means. Anything Is Possible.\",\"protected\":false,\"verified\":false,\"followers_count\":319,\"friends_count\":442,\"listed_count\":12,\"favourites_count\":378,\"statuses_count\":5247,\"created_at\":\"Sat Sep 12 20:29:07 +0000 2009\",\"utc_offset\":-18000,\"time_zone\":\"Central Time (US & Canada)\",\"geo_enabled\":true,\"lang\":\"en\",\"contributors_enabled\":false,\"is_translator\":false,\"profile_background_color\":\"000000\",\"profile_background_image_url\":\"http://abs.twimg.com/images/themes/theme14/bg.gif\",\"profile_background_image_url_https\":\"https://abs.twimg.com/images/themes/theme14/bg.gif\",\"profile_background_tile\":false,\"profile_link_color\":\"0B4040\",\"profile_sidebar_border_color\":\"000000\",\"profile_sidebar_fill_color\":\"000000\",\"profile_text_color\":\"000000\",\"profile_use_background_image\":false,\"profile_image_url\":\"http://pbs.twimg.com/profile_images/490955924693995520/uY90KDh0_normal.jpeg\",\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/490955924693995520/uY90KDh0_normal.jpeg\",\"profile_banner_url\":\"https://pbs.twimg.com/profile_banners/73727137/1386261952\",\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null},\"geo\":null,\"coordinates\":null,\"place\":{\"id\":\"3df4f427b5a60fea\",\"url\":\"https://api.twitter.com/1.1/geo/id/3df4f427b5a60fea.json\",\"place_type\":\"city\",\"name\":\"San Antonio\",\"full_name\":\"San Antonio, TX\",\"country_code\":\"US\",\"country\":\"United States\",\"bounding_box\":{\"type\":\"Polygon\",\"coordinates\":[[[-98.778559,29.141956],[-98.778559,29.693046],[-98.302744,29.693046],[-98.302744,29.141956]]]},\"attributes\":{}},\"contributors\":null,\"retweet_count\":0,\"favorite_count\":0,\"entities\":{\"hashtags\":[],\"trends\":[],\"urls\":[],\"user_mentions\":[],\"symbols\":[]},\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"filter_level\":\"low\",\"lang\":\"en\",\"timestamp_ms\":\"1431614277826\"}";
		
		System.out.println(cipher.encrypt(msg));
		cipher.reset();
		String msg2 = "fypid2xjcm9nWGFwKjsnXWpzKk5meSQ5NSU4Njw5ND01MygqNTkyNioxNzExKi0nYGYkMDY+ODw9OD05MDI6MzU2PDo5PCUgb25cdHR2KjsnPDs+MjY+ODQ6NTU5MDAyMT85JiQjcWx6cig5JUdrZ2UlZG10ZGppZyUo45ysICoocGh1dmtkJzMgOmsjb3JhbjwnYXZyejkoL3B/aHF9Z3QkYGhtK2xucmduaWtnKGFqbHNqYGYkKnFibDkqb2pvbWpmbHAiOlx2bH12Y3gjYW92KEBrbXBpY2c7L2U2IykrdnR/bWRhcG1lJzNkZ2ZwYiwmYW9ae2d2ZnpYdGtXcnFodnN5XG5kJjJvcGVuKihqaV92bXFpcF1yZVx0dGV8dHZWa2JVcHNyJjJvcGVuKihqaV92bXFpcF1yZVxyc2F6XmxtIDxkdmtsKCpoa1ZwY3pvfl9wZ15wemd0VWpjX3d8cyczbHNmbysibWZed2xyanNcc29be2J3bGdoVW1mbWEqO2t8bmomIXJzYXojP3Igb24hPTc3PzMyODExJiFuZFt7dXcrOCQ9MDAyMzkyMisuJGRiamUmMiNEZGBjeCNFcnFmcueVgCQmIXRjdm1ka1ZsZ2dmJTomS25pZnd0W3ZiZWpJQyclIGplYGZ0bWdvJzMgVUtNJ0FKXE5LQE0qKldCWEVbIykrd3RmIT1ucWRtKStmY3lgdWl0fGhqZyA8KEBITEtdUyVYd2NvbSdCKigiTWhrdCpQc3loYXJxKWtoKndvZSRFYG5gbGEkI04gSEdXQClBSUZMVSEkSnglSGx/Kk5iYWp7LyVIbH9+a25uYyhIdilSaXlwbmJobS8nJSB2eGxzZWd8ZGErOGBrb3RlKCp3YHtrYGNmYyI+bmBpemcqKGVobGhndmB7cVlpbHJucCo7Njg7KihldWlhZmV2VmFpf21zIj48NTclIGpjcHNlYFdianxscig5NjIoKmdkf21zeGpzZXdXYmp8bHIoOTQ3PCQjdn1jcn9wYnNba25wZ3YkMDY1NDMkI2Z7Z2d+ZmNfZXwjPytRZ34jVGV0KDA3KTA2MDE+OjQ/IS45MjY6IzUwNDEjKSt3cmlcaGZie2RxKzgrOzs3MDQkI3Fgb2NVeWhuYSo7J0pnaH5xZmwkXGhobCIuX1AnJiRLYGtoZmcjISsiY21uWmxsZ2hvYmQmMnV3fGcqKG9mbmMqOydsbCQmIWRvanxzbGt3cmVxdF9hZmBnZWdiKDlhYWh7ZCkra3VVd3VhanttZH1tdCg5YWFoe2QpK3J0ZWVubGFXY2RqaWF4bHJuYFdiamVtdCg5JTA0ODE1OSAqKHN1b2JhbWBWYGdpaGBya31vYVZra2tkYl9xem0nMyBufnd3OisnYGd6LHJ9ampnKmtuaCZra2tkYnMrfGlgZGd1JXdvZWltMDEmYGEkZG5mJiQjdXttYGNvYl9maWJubnBpf21jX21lYGJsXXN4b1hocHxxdis4JGJ3c3B3Mi4qaGB1JHdwaWlvL2ZmbyljbmZnYXsucWFna29wKHRsbWxgODYpaGQpZ21uIykrcnRlZW5sYVdjZGppYXhscm5gV3VsZWckMGVmbHdtLSd5cGlsamtlW2Roa2JdZWVvaHImMiM1SzY2PjMlLCZ4c2pva2pvXHRpYG1jZHtdZGVxY2V2V2JqZW10KDklMDQ4MTU5ICooc3VvYmFtYFZxb25mZWF2V2dsZW5ZaWxrb3YqOyc5MjY6MzciKCpxd2Zkb2ZmWHRhcHVaam1qZXElOiY4MTU5MjYoLyVwdmdnbGVnWX9wYl9maWJubnBpf21jX21lYGJsIDxsYmtzYSQjdXttYGNvYl9tZWBibF1zeG8lOiZgdXF5OCklc2VzKnx2bGRlKGlsai90em5jYG5jVWpqYWNtcio9OzYzNjI5Njw3PDo7Pz82NTArfVg8OUlCYjNYbmt6bGRlLGx6ZmAiKCpxd2Zkb2ZmWGlpaWZgVnd0ZlxvdHB4ciczIG5+d3dzPicudWtxKH50bm1jJmJqZC12eGxhaWhtXmxkY2FvcCg0PTg4MDw7ND41PjM9MTQwOzIpf1o+ME9MaTVWbGl4bmZsKmJxYG4gKihzdW9iYW1gVmBnZG1iclt9c2krOCRid3NwdzIuKnlgdSR3cGlpby9mZm8penFoZm1kZFprY2hkZnVzKz8yMjs1Nzk0KDE3MDc3PzM/PzElLCZsZGNod2p+XHdya25oaWwgPGxia3NhJCNhbGRnf29zX3R6bmNgbmNVamphY20jP29janlmKyJiZ21pZnVvZGQlOmp9bWklIGBlb2tvc1dzYHh3Y3l3WHNhZnUnM2xzZm8rImpndWxva2Vrd25vansjP2d3amZ+KyJjbW4nM2xzZm8rImdnbndta2hrd2JzJjJvcGVuKihza2FnbSM/ciBvbiE9IjdsZzFvNjQ9YTJhMjhnYGggKih2dWwmMiNtfXZ2eTkoL2V4aCt9dW9+d2JyKmtuaCYzKDssYGVrJ2hhJjFibDdhNDY/YzBoNDZsZmYubntuaysuJHpvZmNhV3V8eWckMCFkaXBxIykrbGdnZiU6JltgaylDaH5saWlrKi0nb3dqZlxpYWltIz8rUWdkI0ZucGdvbGYuJl5bJSwma25wZ3Z0c1xkb2BtIz8rV1UoLyVja31vcXt7JDAhUm5tfGRhKVFya3dicyYkI2dmd2huamlnW2pufSs4fSh3fnBhKjsnWW1qc2RobiYkI2ZmbXRuamlhcG1yJzNZXVEuPjgqPzY9PDc/JjE+LjU8MDw8NFsmWCo5PCY2MjE3MzMvNTkqPjg2OTYwVy9cLT0wLzY5MDE+NysyPSY3PDoyMjxeK1spMTkrOjI0PTczLDYxLzQ9Mz8/NVpdWXUtJ2h2cnhqZXVwbXInM3l7dy8lY2tmdXdgYHN+bHVzJjJvcGVuKihxYnRzbWRxVmFpf21zIj44LSdvY3BlcW50YVdianxscig5NywmbW9xYHZvb3AlOn8qaWR6anJrZHQiPlNcKSt2dG9tY3MmMlpYJSBzeG90Ij5TXCkrd3VvcVhtYWZ1bGZsdSg5XF0oKnJ8ZGBpZnAlOl9VfCkrZGd8bHVpcG1lJzNkZ2ZwYiwmemRxfmdjfmZjIj5uYGl6Zyooc2hzd2FjaXBddW9tdGlwYXdgKzhga290ZSgqZ2xldmN4XGtlcm1tJzMgamV0JSwmZGBrbiA8KGZpIigqdWxkZ3V+YmpwW2VyJzMgNz4wNjY1PDMyPjo0PCF6";
		String something = cipher.decrypt(msg2);
		System.out.println(something);
	}
}
