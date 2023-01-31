package io.github.leopold.exception.v1;

public class ConsumerNotFoundExceptionV1 extends BaseNotFoundExceptionV1 {

    public ConsumerNotFoundExceptionV1(String consumerName) {
        super("Consumer not found by name: '" + consumerName + "'");
    }

}
