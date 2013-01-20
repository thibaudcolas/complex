package com.github.masalthunlass.complex.model;

import java.io.IOException;
import java.io.OutputStream;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class CoolResponse {

	private CoolQuery query;
	private ResultSet rs;
	private long time_model;
	private long memory_model;
	private long time_query;
	private long memory_query;

	public CoolResponse(CoolQuery q, ResultSet r, long tm, long mm, long tq,
			long mq) {
		query = q;
		rs = r;
		time_model = tm;
		memory_model = mm;
		time_query = tq;
		memory_query = mq;
	}

	public CoolQuery getQuery() {
		return query;
	}

	public long getTimeModel() {
		return time_model;
	}

	public long getMemoryModel() {
		return memory_model;
	}

	public long getTimeQuery() {
		return time_query;
	}

	public long getMemoryQuery() {
		return memory_query;
	}

	public void outputAsJSON(OutputStream out) {
		try {
			out.write("{".getBytes());
			out.write("\"stats\": ".getBytes());
			out.write("{".getBytes());
			out.write(("\"time_model\": " + getTimeModel() + ",").getBytes());
			out.write(("\"memory_model\": " + getMemoryModel() + ",")
					.getBytes());
			out.write(("\"time_query\": " + getTimeQuery() + ",").getBytes());
			out.write(("\"memory_query\": " + getMemoryQuery()).getBytes());
			out.write("},".getBytes());
			out.write("\"request\": {".getBytes());
			ResultSetFormatter.outputAsJSON(out, rs);
			out.write("}".getBytes());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
