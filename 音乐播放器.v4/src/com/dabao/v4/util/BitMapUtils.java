package com.dabao.v4.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;

public class BitMapUtils {
	/**
	 * ͨ��һ�������·������һ��ͼƬ
	 * @param path
	 */
	/**
	 * �첽�ڹ����߳���ִ��ͼƬģ��������
	 * 
	 * @param bitmap
	 * @param r
	 * @param callback
	 */
	public static void loadBluredBitmap(final Bitmap bitmap, final int r,
			final BitmapCallback callback) {
		new AsyncTask<String, String, Bitmap>() {
			protected Bitmap doInBackground(String... params) {
				Bitmap b = createBlurBitmap(bitmap, r);
				return b;
			}

			protected void onPostExecute(Bitmap b) {
				callback.onBitmapLoaded(b);
			}
		}.execute();
	}

	/**
	 * ����bitmap ����ģ���뾶 ����һ����ģ����bitmap
	 * 
	 * @param sentBitmap
	 * @param radius
	 * @return
	 */
	public static Bitmap createBlurBitmap(Bitmap sentBitmap, int radius) {
		Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
		if (radius < 1) {
			return (null);
		}
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		int[] pix = new int[w * h];
		bitmap.getPixels(pix, 0, w, 0, 0, w, h);
		int wm = w - 1;
		int hm = h - 1;
		int wh = w * h;
		int div = radius + radius + 1;
		int r[] = new int[wh];
		int g[] = new int[wh];
		int b[] = new int[wh];
		int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
		int vmin[] = new int[Math.max(w, h)];
		int divsum = (div + 1) >> 1;
		divsum *= divsum;
		int dv[] = new int[256 * divsum];
		for (i = 0; i < 256 * divsum; i++) {
			dv[i] = (i / divsum);

		}
		yw = yi = 0;
		int[][] stack = new int[div][3];
		int stackpointer;
		int stackstart;
		int[] sir;
		int rbs;
		int r1 = radius + 1;
		int routsum, goutsum, boutsum;
		int rinsum, ginsum, binsum;
		for (y = 0; y < h; y++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			for (i = -radius; i <= radius; i++) {
				p = pix[yi + Math.min(wm, Math.max(i, 0))];
				sir = stack[i + radius];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rbs = r1 - Math.abs(i);
				rsum += sir[0] * rbs;
				gsum += sir[1] * rbs;
				bsum += sir[2] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];

				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];

				}

			}
			stackpointer = radius;
			for (x = 0; x < w; x++) {
				r[yi] = dv[rsum];
				g[yi] = dv[gsum];
				b[yi] = dv[bsum];
				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;
				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];
				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];
				if (y == 0) {
					vmin[x] = Math.min(x + radius + 1, wm);

				}
				p = pix[yw + vmin[x]];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];
				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;
				stackpointer = (stackpointer + 1) % div;
				sir = stack[(stackpointer) % div];
				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];
				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];
				yi++;

			}
			yw += w;

		}
		for (x = 0; x < w; x++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			yp = -radius * w;
			for (i = -radius; i <= radius; i++) {
				yi = Math.max(0, yp) + x;
				sir = stack[i + radius];
				sir[0] = r[yi];
				sir[1] = g[yi];
				sir[2] = b[yi];
				rbs = r1 - Math.abs(i);
				rsum += r[yi] * rbs;
				gsum += g[yi] * rbs;
				bsum += b[yi] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];

				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];

				}
				if (i < hm) {
					yp += w;
				}
			}
			yi = x;
			stackpointer = radius;
			for (y = 0; y < h; y++) {
				pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
						| (dv[gsum] << 8) | dv[bsum];
				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;
				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];
				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];
				if (x == 0) {
					vmin[y] = Math.min(y + r1, hm) * w;

				}
				p = x + vmin[y];
				sir[0] = r[p];
				sir[1] = g[p];
				sir[2] = b[p];
				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];
				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;
				stackpointer = (stackpointer + 1) % div;
				sir = stack[stackpointer];
				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];
				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];
				yi += w;

			}

		}
		bitmap.setPixels(pix, 0, w, 0, 0, w, h);
		return (bitmap);
	}

	/**
	 * ͨ��һ�������·������һ��ͼƬ
	 * 
	 * @param path
	 */
	public static void loadBitmap(Context context, final String path,
			final int width, final int height, final BitmapCallback callback) {
		// ��ȥ�ļ������� ������û�����ع�
		String filename = path.substring(path.lastIndexOf("/") + 1);
		final File file = new File(context.getCacheDir(), filename);
		Bitmap bitmap = loadBitmap(file.getAbsolutePath());
		if (bitmap != null) {
			callback.onBitmapLoaded(bitmap);
			return;
		}
		// �ļ���û��ͼƬ ��ȥ����
		new AsyncTask<String, String, Bitmap>() {
			protected Bitmap doInBackground(String... params) {
				try {
					InputStream is = HttpUtils.get(path);
					Bitmap b = null;
					if (width == 0 && height == 0) {
						b = BitmapFactory.decodeStream(is);
					} else {
						b = loadBitmap(is, width, height);
					}
					// ͼƬһ�����سɹ� ��Ҫ�����ļ�
					save(b, file.getAbsolutePath());
					return b;
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(Bitmap bitmap) {
				callback.onBitmapLoaded(bitmap);
			}
		}.execute();
	}

	/**
	 * ��ĳ��·���¶�ȡһ��bitmap
	 * 
	 * @param path
	 * @return
	 */
	public static Bitmap loadBitmap(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}
		return BitmapFactory.decodeFile(path);
	}

	/**
	 * ����ͼƬ
	 * 
	 * @param bitmap
	 * @param path
	 *            ͼƬ��Ŀ��·��
	 */
	public static void save(Bitmap bitmap, String path) throws IOException {
		File file = new File(path);
		if (!file.getParentFile().exists()) { // ��Ŀ¼������
			file.getParentFile().mkdirs(); // ������Ŀ¼
		}
		FileOutputStream os = new FileOutputStream(file);
		bitmap.compress(CompressFormat.JPEG, 100, os);
	}

	/**
	 * @param is
	 *            ����Դ
	 * @param width
	 *            ͼƬ��Ŀ����
	 * @param height
	 *            ͼƬ��Ŀ��߶�
	 * @return ѹ�������ͼƬ
	 */
	public static Bitmap loadBitmap(InputStream is, int width, int height)
			throws IOException {
		// ͨ��is ��ȡ ��һ�� byte[]
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = is.read(buffer)) != -1) {
			bos.write(buffer, 0, length);
			bos.flush();
		}
		byte[] bytes = bos.toByteArray();
		// ʹ��BitmapFactory��ȡͼƬ��ԭʼ��͸�
		Options opts = new Options();
		// ��������ͼƬ�ı߽�����
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
		// ͨ��Ŀ���͸߼���ͼƬ��ѹ������
		int w = opts.outWidth / width;
		int h = opts.outHeight / height;
		int scale = w > h ? h : w;
		// ��Options��������ѹ������
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = scale;
		// ���½���byte[] ��ȡBitmap
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
	}

	public interface BitmapCallback {
		void onBitmapLoaded(Bitmap bitmap);
	}

}