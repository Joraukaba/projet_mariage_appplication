package com.mariage.projet.zamak.Mariage_projet_joachim.Exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;


@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException {

  @Getter
  private final Set<String> violations;

  @Getter
  private final String violationSource;

}
