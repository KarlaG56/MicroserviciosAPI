from src.Product.Application.UseCase.Products.Get import GetUseCase as UseCase, Port


class GetController:
    def __init__(self, repository: Port):
        self.__use_case = UseCase(repository)

    def run(self):
        return self.__use_case.run()