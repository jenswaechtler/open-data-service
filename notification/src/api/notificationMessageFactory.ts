import { TransformationEvent } from "./transformationEvent"

export namespace NotificationMessageFactory {

  /**
   * Builds the notification message to be sent,
   * by composing the contents of the transformation event to readable
   * message
   *
   * @param event event to extract transformation results from
   * @returns message to be sent as notification
   */
  export const buildMessage = (event: TransformationEvent): string => {
    let message: string
    if (!event.error) {
      message = `Pipeline ${event.pipelineName} (Pipeline id:${event.pipelineId}) ` +
        `has new data available. Fetch at ${event.dataLocation}.`
    } else {
      message = `Pipeline ${event.pipelineName}(Pipeline ID:${event.pipelineId}) failed. \n` +
          `${JSON.stringify(event.error)}`
    }

    return message
  }
}
