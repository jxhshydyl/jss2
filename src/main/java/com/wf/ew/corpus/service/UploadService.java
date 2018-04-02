package com.wf.ew.corpus.service;

import java.io.InputStream;

public interface UploadService {
    int bulkAddQuestion(InputStream inputStream, String questionType,String fileName);
}
