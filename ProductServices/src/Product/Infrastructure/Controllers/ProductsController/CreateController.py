from src.Product.Application.UseCase.Products.Create import CreateUseCase as UseCase, Port


class CreateController:
    def __init__(self, repository:Port):
        self.__use_case = UseCase(repository)

    def run(self, request):
        return self.__use_case.run(request.get_json())