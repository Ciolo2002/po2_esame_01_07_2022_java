public interface Either<T>{
    public T onSuccess(T t);
    public void onFailure(T t) throws Exception;
}
